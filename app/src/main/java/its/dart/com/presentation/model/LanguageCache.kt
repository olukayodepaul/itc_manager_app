package its.dart.com.presentation.model

class LanguageCache {

    var nodes: ArrayList<Caches> = ArrayList()

    fun add(id: Int, name: String) {
        nodes.add(Caches(id, name))
    }

    fun clear() {
        nodes.clear()
    }

    fun size(): Int {
        return nodes.size
    }

    fun getIndexById(id: Int): Int {
        return nodes.indexOfFirst { it.getId() == id }.takeIf { it >= 0 } ?: -1
    }

    fun getValueId(value: String): Int {
        return nodes.filter { it.getName() == value }
            .maxByOrNull { it.getId() }?.getId() ?: 0  // Get latest ID if multiple exist, otherwise return 0
    }

    class Caches constructor(internal var id: Int, internal var name: String) {
        fun getId(): Int = id
        fun setId(id: Int) { this.id = id }
        fun getName(): String = name
        fun setName(name: String) { this.name = name }
    }
}
