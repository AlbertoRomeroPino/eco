# 🌍 Huella de Carbono

¡Bienvenido a la aplicacion de **Huella de Carbono**! Este proyecto está diseñado para rastrear, analizar y reducir las emisiones de CO₂ mediante el registro de actividades y hábitos cotidianos. Además, proporciona recomendaciones personalizadas para disminuir el impacto ambiental. 🌱

---

## 🛠️ Tecnologías Utilizadas

### Backend
- **Java**: Lenguaje principal para el desarrollo de la lógica de negocio.
- **Hibernate/JPA**: Framework de persistencia para interactuar con la base de datos de forma eficiente.
- **MariaDB**: Base de datos relacional utilizada para almacenar toda la información.

### Interfaz Gráfica
- **JavaFX**: : Framework para la creación de interfaces gráficas modernas y dinámicas en Java.

---

## 📋 Estructura de la Base de Datos

### Tablas Principales

1. **Usuario**  
   Contiene la información básica de los usuarios registrados.
    - **Columnas**:
        - `id_usuario`: Identificador único (PK).
        - `nombre`: Nombre del usuario.
        - `email`: Correo electrónico único.
        - `contraseña`: Contraseña del usuario.
        - `fecha_registro`: Fecha de registro (por defecto, la actual).

2. **Categoría**  
   Agrupa las actividades según su tipo (transporte, energía, alimentación, etc.).
    - **Columnas**:
        - `id_categoria`: Identificador único (PK).
        - `nombre`: Nombre de la categoría.
        - `factor_emision`: Factor de emisión de CO₂ (por unidad).
        - `unidad`: Unidad de medida asociada (km, kWh, kg, etc.).

3. **Actividad**  
   Registra actividades específicas realizadas por los usuarios.
    - **Columnas**:
        - `id_actividad`: Identificador único (PK).
        - `nombre`: Nombre de la actividad.
        - `id_categoria`: Relación con la tabla **Categoría** (FK).

4. **Huella**  
   Representa las emisiones generadas por las actividades de los usuarios.
    - **Columnas**:
        - `id_registro`: Identificador único (PK).
        - `id_usuario`: Relación con la tabla **Usuario** (FK).
        - `id_actividad`: Relación con la tabla **Actividad** (FK).
        - `valor`: Cantidad consumida (por ejemplo, km recorridos o kWh usados).
        - `unidad`: Unidad de consumo (km, kWh, etc.).
        - `fecha`: Fecha de registro.

5. **Habito**  
   Relación intermedia para conectar usuarios y actividades frecuentes.
    - **Columnas**:
        - `id_usuario`: Relación con la tabla **Usuario** (FK).
        - `id_actividad`: Relación con la tabla **Actividad** (FK).
        - `frecuencia`: Cantidad de veces que se realiza la actividad.
        - `tipo`: Frecuencia (diaria, semanal, mensual, anual).
        - `ultima_fecha`: Fecha de la última actividad registrada.

6. **Recomendación**  
   Ofrece consejos prácticos para reducir las emisiones de CO₂.
    - **Columnas**:
        - `id_recomendacion`: Identificador único (PK).
        - `id_categoria`: Relación con la tabla **Categoría** (FK).
        - `descripcion`: Descripción de la recomendación.
        - `impacto_estimado`: Reducción potencial de CO₂ en kg.  