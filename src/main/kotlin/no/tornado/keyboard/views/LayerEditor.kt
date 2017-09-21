package no.tornado.keyboard.views

import javafx.scene.paint.Color
import no.tornado.keyboard.models.LayerModel
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
                                key {
                                    val (kw, kh, kc, addSpacer) = layer.keyprops(key)
                                    keyWidth = kw
                                    keyHeight = kh
                                    background = kc.asBackground()
                                    textProperty().bind(key.codeProperty.asString())
                                    if (addSpacer) spacer()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
