package no.tornado.keyboard.views

import javafx.scene.paint.Color
import no.tornado.keyboard.models.*
import tornadofx.*

class LayerEditor : View() {
    val layer: LayerModel by inject()

    init {
        titleProperty.bind(layer.name)
    }

    override val root = borderpane {
        center {
            stackpane {
                paddingAll = 40
                keyboard {
                    background = Color.TRANSPARENT.asBackground()
                    widthProperty().onChange {
                        unitSize = it / 14.3
                    }
                    layer.item.rows.forEach { row ->
                        row {
                            row.forEach { key ->
                                addKey(key)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun KeyboardRow.addKey(key: Key) {
        key {
            val (kw, kh, kc, addSpacer) = layer.keyprops(key)
            keyWidth = kw
            keyHeight = kh
            background = kc.asBackground()
            textProperty().bind(key.descriptionProperty)
            graphicProperty().bind(key.graphicProperty)
            if (addSpacer) spacer()
            action {
                editKey(this, key)
            }
        }
    }

    private fun editKey(keyNode: KeyboardKey, key: Key) {
        val model = KeyModel(key)
        builderWindow("Edit key") {
            form {
                fieldset {
                    field("Key code") {
                        combobox(model.code, KeyCode.values().toList()) {
                            converter = KeyCodeConverter
                        }
                    }
                }
                button("Save").action {
                    model.commit {
                        close()
                    }
                }
            }
        }

    }

    override fun onSave() {
    }
}
