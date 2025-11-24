package Modelo;

import Historial.IRepositorioResultados;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RuletaTest {

    @Test
    void testConstructorRechazaSaldoInicialNegativo() {
        // Arrange
        IRepositorioResultados repositorioDummy = null; // No se usa en la validación
        int saldoInvalido = -100;

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Ruleta(repositorioDummy, saldoInvalido);
        });

        assertEquals("Saldo inicial inválido", exception.getMessage());
    }

    @Test
    void testDepositoValidoIncrementaSaldo() {
        // Arrange
        IRepositorioResultados repositorioDummy = null;
        Ruleta ruleta = new Ruleta(repositorioDummy, 1000);
        int montoDeposito = 500;

        // Act
        ruleta.depositar(montoDeposito);

        // Assert
        assertEquals(1500, ruleta.getSaldo());
    }

    @Test
    void testJugarLanzaExcepcionSiApuestaEsNull() {
        // Arrange
        IRepositorioResultados repositorioDummy = null;
        Ruleta ruleta = new Ruleta(repositorioDummy, 1000);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ruleta.jugar(null);
        });

        assertEquals("Apuesta requerida", exception.getMessage());
    }

    @Test
    void testJugarRechazaApuestaMayorAlSaldo() {
        // Arrange
        IRepositorioResultados repositorioDummy = null;
        Ruleta ruleta = new Ruleta(repositorioDummy, 100);
        Apuesta apuestaExcesiva = new Apuesta(200, TipoApuesta.ROJO);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ruleta.jugar(apuestaExcesiva);
        });

        assertEquals("Saldo insuficiente", exception.getMessage());
    }
}
