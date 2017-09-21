package no.tornado.keyboard.app

import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val keymapName by cssclass()
    }

    init {
        textField and keymapName {
            padding = box(10.px)
            fontSize = 20.px
            fontWeight = FontWeight.BOLD
            backgroundColor += Color.TRANSPARENT
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