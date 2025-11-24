package Controlador;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SessionControllerTest {

    @Test
    void testInicioSesionUsuarioNoRegistrado() {
        // Arrange
        SessionController session = new SessionController();

        // Act & Assert
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            session.iniciarSesion("usuarioFantasma", "1234");
        });

        assertEquals("Credenciales incorrectas", exception.getMessage());
    }

    @Test
    void testInicioSesionConUsernameNull() {
        // Arrange
        SessionController session = new SessionController();
        session.registrarUsuario("validUser", "pass", "Name");

        // Act & Assert
        // iniciarSesion(null) lanzará IllegalStateException porque no encontrará al
        // usuario null en el mapa
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            session.iniciarSesion(null, "pass");
        });

        assertEquals("Credenciales incorrectas", exception.getMessage());
    }

    @Test
    void testRegistroUsuarioDuplicado() {
        // Arrange
        SessionController session = new SessionController();
        session.registrarUsuario("user1", "pass", "User One");

        // Act & Assert
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            session.registrarUsuario("user1", "newPass", "New Name");
        });

        assertEquals("Usuario ya registrado", exception.getMessage());
    }
}
