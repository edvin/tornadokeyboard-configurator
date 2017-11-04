package no.tornado.keyboard.models

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
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

    val descriptionProperty = SimpleStringProperty()
    val modifierDescriptionProperty = SimpleStringProperty()

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
        updateDescription()
    }

    fun updateDescription() {
        val desc = StringBuilder()

        val k = when (code?.group) {
            KeyCodeGroup.LayerSwitching -> "${code.name}\nL$layer"
            null -> ""
            else -> code.description
        }
        desc.append(k)
        descriptionProperty.value = desc.toString()

        modifierDescriptionProperty.value = if (modifiers.isNotEmpty())
             modifiers.map { it.description }.joinToString("+") + "\n"
        else
            null

    }

    init {
        updateDescription()
    }
}

class KeyModel(key: Key? = null) : ItemViewModel<Key>(key) {
    val modifiers = bind(Key::modifiersProperty)
    val layer = bind(Key::layerProperty)
    val layerToggleType = bind(Key::layerToggleTypeProperty)
    val code = bind(Key::codeProperty)
    val description = bind(Key::descriptionProperty)

    override fun onCommit() {
        item?.updateDescription()
    }
}