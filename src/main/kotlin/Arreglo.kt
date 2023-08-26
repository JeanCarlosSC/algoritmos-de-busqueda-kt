import java.io.Serializable
import kotlin.math.pow

class Arreglo(
    private var nDigitos: Int,
    private var n: Int //size
): Serializable {
    private val v = mutableListOf<Int>() // claves

    fun getMaxValue(): Int {
        return 10.0.pow(nDigitos).toInt()-1
    }

    fun buscarSecuencialmente(x: Int) {
        var i=0
        while ((i<v.size) && (v[i] != x)) {
            i++
        }
        if(i>=v.size){
            println("La clave ${"%0${nDigitos}d".format(x)} no está en la estructura")
        }
        else {
            println("La clave ${"%0${nDigitos}d".format(x)} ha sido encontrada en la posición ${i+1}")
        }
    }

    fun insertar(x: Int): Boolean {
        return if (v.size >= n) {
            println("La clave $x no puede ser insertada porque la estructura de datos esta llena")
            false
        } else {
            v.add(x)
            println("La clave $x ha sido agregada satisfactoriamente\n")
            true
        }
    }

    override fun toString(): String {
        var str = ""
        for(i in 0..<v.size) {
            str += "\t  posición: ${i+1}  clave: ${"%0${nDigitos}d".format(v[i])}\n"
        }
        return str
    }
}