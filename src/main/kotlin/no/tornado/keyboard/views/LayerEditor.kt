package no.tornado.keyboard.views

import javafx.beans.binding.Bindings
import javafx.geometry.Orientation
import javafx.geometry.Pos
import javafx.scene.control.ListCell
import javafx.scene.paint.Color
import javafx.scene.text.Text
import javafx.stage.Modality
import javafx.stage.StageStyle
import no.tornado.keyboard.app.Styles
import no.tornado.keyboard.models.*
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
            graphic = vbox {
                alignment = Pos.CENTER
                label(key.descriptionProperty).isWrapText = true
                label(key.modifierDescriptionProperty) {
                    addClass(Styles.grey, Styles.small)
                    removeWhen(key.modifierDescriptionProperty.isEmpty)
                }
            }
            if (addSpacer) spacer()
            action {
                editKey(key)
            }
        }
    }


    private fun editKey(key: Key) {
        val model = KeyModel(key)
        builderWindow("Edit key", modality = Modality.WINDOW_MODAL, stageStyle = StageStyle.UTILITY) {
            form {
                paddingAll = 20
                prefWidth = 370.0
                fieldset(labelPosition = Orientation.VERTICAL) {
                    field("Key code") {
                        combobox(model.code, KeyCode.values().toList().filter { it.isSelectable }) {
                            cellFormat(formatButtonCell = false) {
                                graphic = vbox {
                                    maxWidth = 200.0
                                    hbox(10) {
                                        label(it.group.name).addClass(Styles.grey)
                                        text(it.description)
                                    }
                                    if (it.info != null) {
                                        label(it.info) {
                                            addClass(Styles.darkgrey)
                                            isWrapText = true
                                        }
                                    }
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
                    val isLayerSwitchingKey = model.code.booleanBinding { it?.group == KeyCodeGroup.LayerSwitching }

                    field("Layer") {
                        removeWhen { !isLayerSwitchingKey }
                        combobox(model.layer, values = IntRange(1, layer.item.keymap.layers.size).toList())
                    }

                    field("Modifiers") {
                        removeWhen { isLayerSwitchingKey }
                        datagrid(KeyCode.selectableKeyCodes) {
                            cellFormat {
                                text = it.info
                            }
                            multiSelect = true
                            cellWidth = 90.0
                            cellHeight = 30.0
                            prefHeight = 95.0

                            runLater {
                                model.modifiers.value.forEach {
                                    selectionModel.select(it)
                                }
                                Bindings.bindContent(model.modifiers.value, selectionModel.selectedItems)
                            }
                        }
                    }
                    hbox(spacing = 6) {
                        val infoBinding = model.code.stringBinding { it?.info }
                        infoBinding.onChange { currentWindow?.sizeToScene() }
                        removeWhen { infoBinding.isEmpty }
                        alignment = Pos.TOP_LEFT
                        svgicon(Styles.questionMark)
                        text(infoBinding) {
                            wrappingWidth = 280.0
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
