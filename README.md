# Sistema de Biblioteca en Equipo

Sistema desarrollado en Java para la gestión integral de libros, socios y préstamos, con control de multas por retraso e interfaz gráfica basada en `JOptionPane`. Este proyecto está diseñado para el trabajo colaborativo en equipo mediante una arquitectura modular basada en el patrón MVP y contratos de interfaces.

---

## 📂 Estructura del Proyecto

El código fuente se organiza bajo el siguiente esquema de paquetes en `com.uptc`:

```text
com.uptc/
├── models/         # Entidades del negocio (Libros, Socios, Préstamos, etc.)
├── logic/          # Interfaces de servicio y lógica de negocio
├── view/           # Interfaz gráfica de usuario con JOptionPane
├── presenter/      # Patrón MVP (Conexión entre Vista y Lógica)
├── exceptions/     # Excepciones personalizadas del dominio
└── run/            # Clase principal para la ejecución del programa
