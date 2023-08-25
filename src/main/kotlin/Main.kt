import sRAD_kt.logic.extention.Extension.isInt
import kotlin.system.exitProcess

var estructura: Estructura? = null

fun main() {
    println("\nBienvenido al sistema de almacenamiento SJ")
    gotoMenu()
}

fun gotoMenu() {
    println("- - - - Menú principal - - - -")
    println("Por favor ingrese el número de la acción que desea ejecutar")
    val hayEstructura = hayEstructura()
    if(hayEstructura) {
        println("1. Ingresar claves\n" +
                "2. Ver claves\n" +
                "3. Buscar claves\n" +
                "4. Modificar claves\n" +
                "5. Eliminar claves\n" +
                "6. Modificar estructura\n" +
                "7. Eliminar estructura\n" +
                "8. Terminar ejecución")
    }
    else {
        println("1. Crear estructura\n" +
                "2. Terminar ejecución")
    }
    val option = readlnOrNull()
    if(option == null || !isInt(option) || option.toInt()<1 || option.toInt()>8
        || (!hayEstructura && option.toInt()>2)) {
        println("Por favor ingrese una de las siguientes opciones")
        gotoMenu()
    }
    else {
        val nOption = option.toInt()
        if(!hayEstructura) {
            when(nOption) {
                1 -> crearEstructura()
                else -> terminarEjecucion()
            }
        }
        else {
            when(nOption) {
                1 -> ingresarClaves()
                2 -> verClaves()
                3 -> buscarClaves()
                4 -> modificarClaves()
                5 -> eliminarClaves()
                6 -> modificarEstructura()
                7 -> eliminarEstructura()
                else -> terminarEjecucion()
            }
        }
    }
}

fun crearEstructura() {
    println("\n- - - - Creando estructura - - - -")

    print("Ingrese la cantidad de dígitos de cada clave: ")
    val nDigitos: Int = readPositiveInt()

    print("Ingrese el tamaño/capacidad de la estructura: ")
    val size: Int = readPositiveInt()

    print("Ingrese el rango de la estructura: ")
    val rango: Int = readPositiveInt()

    estructura = Estructura(nDigitos, size, rango)
    println("Estructura creada satisfactoriamente")
    gotoMenu()
}

fun ingresarClaves() {
    println("Ingresando claves")
}

fun verClaves() {
    println("Mostrando claves")
}

fun buscarClaves() {
    println("Buscando claves")
}

fun modificarClaves() {
    println("Modificando claves")
}

fun eliminarClaves() {
    println("Eliminando claves")
}

fun modificarEstructura() {
    println("Modificando estructura")
}

fun eliminarEstructura() {
    println("Eliminando estructura")
}

fun terminarEjecucion() {
    println("Ejecución terminada exitosamente")
    exitProcess(0)
}

fun hayEstructura(): Boolean {
    return estructura != null
}

fun readPositiveInt(): Int {
    val num: Int
    do {
        val input = readlnOrNull()
        if(input == null || !isInt(input) || input.toInt()<1) {
            println("Por favor ingrese un valor entero positivo")
        }
        else {
            num = input.toInt()
            break
        }
    } while (true)
    return num
}