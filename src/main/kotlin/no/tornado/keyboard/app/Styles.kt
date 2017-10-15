package no.tornado.keyboard.app

import javafx.geometry.Pos
import javafx.scene.layout.BorderStrokeStyle
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val keymapName by cssclass()
        val grey by cssclass()
    }

    init {
        grey {
            textFill = Color.GREY
        }
        textField and keymapName {
            padding = box(10.px)
            fontSize = 20.px
            fontWeight = FontWeight.BOLD
            backgroundColor += Color.TRANSPARENT
        }

        labelContainer {
            fontWeight = FontWeight.BOLD
        }

        titledPane {
            title {
                fontWeight = FontWeight.BOLD
                backgroundColor += Color.TRANSPARENT
            }
            content {
                backgroundColor += Color.TRANSPARENT
                borderWidth += box(0.px)
            }
        }
        datagridCell {
            alignment = Pos.CENTER
        }
        datagrid {
            borderStyle += BorderStrokeStyle.NONE
        }
        comboBox {
            selected {
                textFill = Color.WHITE
            }
        }
        tabPane {
            tab {
                tabLabel {
                    fontSize = 14.px
                }
            }
        }
    }
}