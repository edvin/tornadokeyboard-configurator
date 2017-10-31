package no.tornado.keyboard.app

import javafx.geometry.Pos
import javafx.scene.layout.BorderStrokeStyle
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val keymapName by cssclass()
        val layerName by cssclass()
        val grey by cssclass()
        val darkgrey by cssclass()

        val questionMark = "M15.07,11.25L14.17,12.17C13.45,12.89 13,13.5 13,15H11V14.5C11,13.39 11.45,12.39 12.17,11.67L13.41,10.41C13.78,10.05 14,9.55 14,9C14,7.89 13.1,7 12,7A2,2 0 0,0 10,9H8A4,4 0 0,1 12,5A4,4 0 0,1 16,9C16,9.88 15.64,10.67 15.07,11.25M13,19H11V17H13M12,2A10,10 0 0,0 2,12A10,10 0 0,0 12,22A10,10 0 0,0 22,12C22,6.47 17.5,2 12,2Z"
    }

    init {
        grey {
            textFill = Color.GREY
        }
        darkgrey {
            textFill = Color.DARKGREY
        }
        textField and keymapName {
            padding = box(10.px)
            fontSize = 20.px
            fontWeight = FontWeight.BOLD
            backgroundColor += Color.TRANSPARENT
        }

        textField and layerName {
            fontSize = 14.px
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