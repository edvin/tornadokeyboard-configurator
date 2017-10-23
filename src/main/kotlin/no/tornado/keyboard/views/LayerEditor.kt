package no.tornado.keyboard.views

import javafx.beans.binding.Bindings
import javafx.geometry.Orientation
import javafx.scene.control.ListCell
import javafx.scene.paint.Color
import javafx.scene.text.Text
import no.tornado.keyboard.app.Styles
import no.tornado.keyboard.models.Key
import no.tornado.keyboard.models.KeyCode
import no.tornado.keyboard.models.KeyModel
import no.tornado.keyboard.models.LayerModel
import tornadofx.*
import tornadofx.Stylesheet as s

class LayerEditor : View() {
    val layer: LayerModel by inject()

    init {
        titleProperty.bind(layer.name)
    }

    override val root = borderpane {
        top {
            paddingAll = 10
            textfield(layer.name).addClass(Styles.layerName)
        }
        center {
            stackpane {
                paddingAll = 30
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
                prefWidth = 350.0
                fieldset(labelPosition = Orientation.VERTICAL) {
                    field("Key code") {
                        combobox(model.code, KeyCode.values().toList().filter { it.isSelectable }) {
                            cellFormat(formatButtonCell = false) {
                                graphic = hbox(10) {
                                    label(it.group.name).addClass(Styles.grey)
                                    text(it.description)
                                }
                            }
                            buttonCell = object : ListCell<KeyCode>() {
                                override fun updateItem(item: KeyCode?, empty: Boolean) {
                                    super.updateItem(item, empty)
                                    graphic = Text(item?.description)
                                }
                            }
                        }
                    }

                    titledpane("Modifiers") {
                        prefHeight = 130.0
                        isAnimated = false
                        datagrid(KeyCode.values().filter { it.isModifier }) {
                            cellFormat {
                                text = it.description
                            }
                            multiSelect = true
                            cellWidth = 90.0
                            cellHeight = 30.0

                            runLater {
                                key.modifiers.forEach {
                                    selectionModel.select(it)
                                }
                                Bindings.bindContent(model.modifiers.value, selectionModel.selectedItems)
                            }
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
