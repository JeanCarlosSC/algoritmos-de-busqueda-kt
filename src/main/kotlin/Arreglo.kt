import java.io.Serializable
import kotlin.math.pow

class Arreglo(
    private var nDigitos: Int,
    private var size: Int //size
): Serializable {
    private val v = mutableListOf<Int>() // claves

    fun getMaxValue(): Int {
        return 10.0.pow(nDigitos).toInt()-1
    }

    fun getSize(): Int {
        return size
    }

    fun setSize(size: Int) {
        this.size = size
    }

    fun setNDigits(nDigitos: Int) {
        this.nDigitos = nDigitos
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

    fun buscarBinariamente(x: Int) {
        // requisitos
        v.sort()

        // definición de variables
        val n = v.size
        var izq: Int = 0
        var der: Int = n-1
        var cen: Int = -1
        var ban: Boolean = false

        // algoritmo
        while((izq <= der) && !ban) {
            cen = (der/2.0 + izq/2.0).toInt()
            if(x == v[cen]) {
                ban = true
            }
            else {
                if(x>v[cen]) {
                    izq = cen + 1
                }
                else {
                    der = cen - 1
                }
            }
        }
        if(ban) {
            println("La información está en la posición ${cen+1}")
        }
        else {
            println("La información no se encuentra en el arreglo")
        }
    }

    fun insertar(x: Int): Boolean {
        return if (v.size >= size) {
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
            str += "\tposición ${i+1}, clave ${"%0${nDigitos}d".format(v[i])}\n"
        }
        return str
    }
}