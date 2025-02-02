import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


enum class Estado {
    PENDIENTE,
    REALIZADA
}


data class Tarea(
    val id: Int,
    val descripcion: String,
    var estado: Estado,
    var fechaRealizada: String? = null)


class GestorTareas {

    val tareas = mutableListOf<Tarea>()
    var idContador = 1

    fun agregarTarea(descripcion: String) {
        val tarea = Tarea(idContador, descripcion, Estado.PENDIENTE)
        tareas.add(tarea)
        idContador++
        println("Tarea agregada con ID: ${tarea.id}")
    }


    fun eliminarTarea(id: Int) {

        val tarea = tareas.find { it.id == id }
        if (tarea != null) {
            tareas.remove(tarea)
            println("Tarea con ID: $id eliminada.")

        } else {
            println("No se encontró la tarea con ID: $id.")
        }
    }


    fun cambiarEstado(id: Int) {
        val tarea = tareas.find { it.id == id }
        if (tarea != null) {
            if (tarea.estado == Estado.PENDIENTE) {

                tarea.estado = Estado.REALIZADA
                tarea.fechaRealizada = obtenerFechaHoraActual()
                println("Tarea con ID: $id marcada como realizada.")
            } else {
                println("La tarea con ID: $id ya está realizada.")
            }
        } else {
            println("No se encontró la tarea con ID: $id.")
        }
    }

    fun mostrarTareas() {
        if (tareas.isEmpty()) {
            println("No hay tareas en la lista.")
        } else {
            println("Lista de todas las tareas: ")
            tareas.forEach { println(it) }
        }
    }


    fun mostrarTareasPendientes() {
        val tareasPendientes = tareas.filter { it.estado == Estado.PENDIENTE }
        if (tareasPendientes.isEmpty()) {
            println("No hay tareas pendientes.")

        } else {
            println("Lista de tareas pendientes:")
            tareasPendientes.forEach { println(it) }
        }
    }

    fun mostrarTareasRealizadas() {
        val tareasRealizadas = tareas.filter { it.estado == Estado.REALIZADA }
        if (tareasRealizadas.isEmpty()) {
            println("No hay tareas realizadas.")
        } else {
            println("Lista de tareas realizadas:")
            tareasRealizadas.forEach { println(it) }
        }
    }


    private fun obtenerFechaHoraActual(): String {
        val fechaHoraActual = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
        return fechaHoraActual.format(formatter)
    }
}


fun main() {
    val gestorTareas = GestorTareas()
    var opcion: Int

    do {
        println("\n--- Menú de gestión de tareas ---")
        println("1. Agregar una tarea")
        println("2. Eliminar una tarea")
        println("3. Cambiar el estado de una tarea")
        println("4. Mostrar todas las tareas")
        println("5. Mostrar las tareas pendientes")
        println("6. Mostrar las tareas realizadas")
        println("7. Salir")
        println("Seleccione una opción:")

        opcion = readLine()?.toIntOrNull() ?: 0

        when (opcion) {
            1 -> {
                println("Ingrese la descripción de la tarea:")
                val descripcion = readLine() ?: ""
                gestorTareas.agregarTarea(descripcion)
            }
            2 -> {
                println("Ingrese el ID de la tarea a eliminar:")
                val id = readLine()?.toIntOrNull() ?: -1
                gestorTareas.eliminarTarea(id)
            }
            3 -> {
                println("Ingrese el ID de la tarea para cambiar su estado:")
                val id = readLine()?.toIntOrNull() ?: -1
                gestorTareas.cambiarEstado(id)
            }
            4 -> gestorTareas.mostrarTareas()
            5 -> gestorTareas.mostrarTareasPendientes()
            6 -> gestorTareas.mostrarTareasRealizadas()
            7 -> println("Saliendo...")
            else -> println("Opción no válida, por favor intente de nuevo.")
        }
    } while (opcion != 7)
}

