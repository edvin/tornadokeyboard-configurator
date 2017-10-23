package no.tornado.keyboard.models

import javafx.beans.property.SimpleStringProperty
import javafx.scene.paint.Color
import tornadofx.*
import javax.json.JsonObject

class Layer : JsonModel {
    val nameProperty = SimpleStringProperty()
    var name by nameProperty

    val keys = MutableList(62) { Key(KeyCode.KC_TRNS) }

    val rows: List<List<Key>>
        get() = rowSeparators.map { keys.subList(it.first, Math.min(it.last + 1, keys.size)) }

    override fun updateModel(json: JsonObject) {
        name = json.string("name")
        json.jsonArray("keys")
                ?.toModel<Key>()
                ?.forEachIndexed { index, key -> keys[index] = key }
    }

    companion object {
        val R1 = 0..13
        val R2 = 14..26
        val R3 = 27..40
        val R4 = 41..52
        val R5 = 53..61
        val rowSeparators = listOf(R1, R2, R3, R4, R5)
    }
}

class LayerModel(layer: Layer) : ItemViewModel<Layer>(layer) {
    val name = bind(Layer::nameProperty)

    fun keyprops(key: Key): KeyProp {
        val index = item.keys.indexOf(key)

        return when (index) {
            0 -> KeyProp(1.25, 1.0, redKey)
            14, 27, 41, 53, 54, 55, 58, 59, 60, 61 -> KeyProp(1.25, 1.0, darkGreyKey)
            52 -> KeyProp(2.0, 1.0, darkGreyKey)
            56, 57 -> KeyProp(2.75, 1.0, lightGreyKey)
            6, 33 -> KeyProp(1.0, 2.0, blueKey)
            else -> KeyProp(1.0, 1.0, lightGreyKey, index in addSpacerAfter)
        }
    }

    companion object {
        val addSpacerAfter = listOf(20, 47)
        val redKey = c("#b81b24")
        val blueKey = c("#0075ad")
        val darkGreyKey = c("#909596")
        val lightGreyKey = Color.LIGHTGREY
    }
}

data class KeyProp(val w: Double, val h: Double, val c: Color, val addBlank: Boolean = false)