package no.tornado.keyboard.models

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.value.ObservableValue
import javafx.collections.FXCollections
import javafx.scene.Node
import tornadofx.*
import javax.json.JsonObject
import javax.json.JsonString

enum class LayerToggleType(val actionName: String) {
    Toggle("ACTION_LAYER_TOGGLE"),
    Momentary("ACTION_LAYER_MOMENTARY")
}

class Key(_code: KeyCode? = null) : JsonModel {
    val modifiersProperty = SimpleListProperty<KeyCode>(FXCollections.observableArrayList())
    var modifiers by modifiersProperty

    val layerProperty = SimpleIntegerProperty()
    var layer: Int? by layerProperty

    val layerToggleTypeProperty = SimpleObjectProperty<LayerToggleType>()
    var layerToggleType by layerToggleTypeProperty

    val codeProperty = SimpleObjectProperty<KeyCode>(_code)
    var code by codeProperty

    val descriptionProperty = stringBinding(codeProperty) {
        code?.description ?: ""
    }

    val graphicProperty: ObservableValue<Node?> = objectBinding(codeProperty) {
        null
    }

    override fun updateModel(json: JsonObject) {
        with(json) {
            if (containsKey("modifiers"))
                modifiers.setAll(getJsonArray("modifiers")
                        .map { it as JsonString }
                        .map { KeyCode.valueOf(it.string) })

            if (containsKey("layer"))
                layer = getInt("layer")

            if (containsKey("layerToggleType"))
                layerToggleType = LayerToggleType.valueOf(getString("layerToggleType"))

            if (containsKey("code"))
                code = KeyCode.valueOf(getString("code"))
        }
    }
}

class KeyModel(key: Key? = null) : ItemViewModel<Key>(key) {
    val modifiers = bind(Key::modifiersProperty)
    val layer = bind(Key::layerProperty)
    val layerToggleType = bind(Key::layerToggleTypeProperty)
    val code = bind(Key::codeProperty)
    val description = bind(Key::descriptionProperty)
    val graphic = bind(Key::graphicProperty)
}