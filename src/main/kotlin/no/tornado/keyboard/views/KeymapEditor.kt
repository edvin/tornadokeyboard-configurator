package no.tornado.keyboard.views

import javafx.scene.control.Tab
import javafx.scene.control.TabPane
import no.tornado.keyboard.app.Styles
import no.tornado.keyboard.models.KeymapModel
import no.tornado.keyboard.models.Layer
import no.tornado.keyboard.models.LayerModel
import tornadofx.*

class KeymapEditor : View("TornadoKeyboard Layout Editor") {
    val keymap: KeymapModel by inject()

    override val root = borderpane {
        setPrefSize(1024.0, 600.0)
        top {
            textfield(keymap.item.nameProperty).addClass(Styles.keymapName)
        }
        center {
            tabpane {
                for (layer in keymap.item.layers)
                    addLayerTab(layer)

                tab("+") {
                    isClosable = false
                    whenSelected {
                        val newTab = addLayerTab(keymap.addLayer())
                        tabs.move(newTab, tabs.size - 2)
                        selectionModel.select(newTab)
                    }
                }
            }
        }
    }

    fun TabPane.addLayerTab(layer: Layer): Tab {
        val scope = Scope(LayerModel(layer))
        val editor = find<LayerEditor>(scope)
        return tab(editor) {
            closeableWhen(booleanBinding(keymap.item.layers) { indexOf(layer) > 0 })
            setOnCloseRequest { event ->
                event.consume()
                confirm("Confirm layer removal",
                        "Are you sure you want to remove this layer? This operation cannot be undone.") {
                    keymap.removeLayer(layer)
                    removeFromParent()
                }
            }
        }
    }
}
