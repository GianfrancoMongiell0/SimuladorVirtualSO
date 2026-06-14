<div align="center">

# 💾 Simulador de Sistema de Archivos y Memoria Virtual

### Sistemas Operativos II — Universidad Metropolitana (UNIMET)

[![Java](https://img.shields.io/badge/Java-SE-ED8B00?style=flat-square&logo=openjdk)](https://java.com)
[![Gson](https://img.shields.io/badge/Gson-Persistencia%20JSON-4285F4?style=flat-square)](https://github.com/google/gson)
[![Swing](https://img.shields.io/badge/GUI-Java%20Swing-ED8B00?style=flat-square)](https://docs.oracle.com/javase/tutorial/uiswing)
[![Universidad](https://img.shields.io/badge/UNIMET-Ing.%20Sistemas-004A8F?style=flat-square)](https://www.unimet.edu.ve)

**Simulador de escritorio que modela un sistema de archivos jerárquico con asignación de memoria por bloques encadenados, persistencia de estado en JSON y logging de operaciones por usuario.**

</div>

---

## ¿Qué es?

Este proyecto simula dos conceptos fundamentales de los sistemas operativos: la **gestión de memoria mediante bloques** y un **sistema de archivos jerárquico**. El usuario puede crear directorios y archivos, asignarles bloques de memoria, guardar el estado completo en JSON y restaurarlo en la siguiente sesión — exactamente como lo haría un SO real con su sistema de archivos.

Incluye dos modos de acceso (Administrador y Usuario), logging persistente de cada acción y una capa de serialización personalizada para estructuras de datos propias.

---

## Features

- 📁 **Sistema de archivos jerárquico** — árbol de directorios y archivos con operaciones CRUD completas
- 🧱 **Gestión de memoria por bloques encadenados** — 100 bloques iniciales asignados y liberados dinámicamente
- 💾 **Persistencia JSON con Gson** — guarda y restaura el estado completo entre sesiones
- 📋 **Logger de auditoría** — registra cada operación con timestamp, usuario y acción en `log.txt`
- 👤 **Dos modos de usuario** — Administrador (acceso total) y Usuario (acceso restringido)
- 🔄 **Generación aleatoria** — crea directorios y archivos de prueba automáticamente
- 🖥️ **GUI Swing** — interfaz visual completa para operar el simulador

---

## Arquitectura

```
src/
│
├── Clases/
│   ├── Bloque.java           # Unidad mínima de memoria — id, estado, puntero al siguiente
│   ├── MemoryManager.java    # Gestiona 100 bloques: asignación encadenada y liberación
│   ├── Archivo.java          # Entidad archivo — nombre, tamaño, bloques asignados, fecha
│   ├── Directorio.java       # Entidad directorio — lista de archivos y subdirectorios
│   ├── SistemaArchivo.java   # Árbol de directorios + MemoryManager; guarda/carga estado
│   ├── LoggerSistema.java    # Logger estático — escribe en log.txt con timestamp
│   └── Main.java             # Entry point — lanza la GUI
│
├── Gson/
│   ├── GsonManager.java          # Orquesta serialización/deserialización del sistema completo
│   ├── ListaAdapter.java         # Adapter custom: serializa Lista<T> propia como JsonArray
│   ├── QueueAdapter.java         # Adapter custom: serializa Queue<T> propia como JsonArray
│   └── LocalDateTimeAdapter.java # Adapter: convierte LocalDateTime ↔ ISO string
│
├── EstructurasDeDatos/
│   ├── Lista.java            # Lista enlazada genérica propia
│   ├── Nodo.java
│   └── Queue.java            # Cola FIFO genérica propia
│
└── Interfaces/
    ├── Simulador.java        # GUI principal (Swing)
    ├── CrearArchivo.java     # Diálogo de creación de archivo
    └── CrearDirectorio.java  # Diálogo de creación de directorio
```

---

## Gestión de memoria — bloques encadenados

Cada archivo ocupa uno o más **bloques de memoria** enlazados entre sí como una lista encadenada. El `MemoryManager` mantiene una cola de bloques libres y una lista de todos los bloques para poder reconstruir los enlaces al deserializar.

```java
// MemoryManager.java — asignación encadenada
public Lista<Bloque> asignarBloquesEncadenados(int cantidad) {
    Lista<Bloque> asignados = new Lista<>();
    Bloque anterior = null;
    for (int i = 0; i < cantidad; i++) {
        Bloque bloque = bloquesLibres.dequeue();
        bloque.setOcupado(true);
        if (anterior != null) {
            anterior.setSiguienteBloque(bloque); // enlace entre bloques
        }
        asignados.insertLast(bloque);
        anterior = bloque;
    }
    return asignados;
}
```

Al eliminar un archivo, los bloques se marcan como libres y la cola de disponibles se reconstruye:

```java
public void liberarBloques(Lista<Bloque> bloques) {
    for (int i = 0; i < bloques.getLength(); i++) {
        bloques.get(i).setOcupado(false);
        bloques.get(i).setSiguienteBloque(null);
    }
    reconstruirColaBloquesLibres();
}
```

---

## Sistema de archivos jerárquico

El árbol de directorios se recorre recursivamente. La búsqueda de un directorio por nombre y la liberación de recursos al eliminar un directorio (incluyendo todos sus subdirectorios y archivos anidados) se hacen con recursión:

```java
// SistemaArchivo.java — búsqueda recursiva
public Directorio buscarDirectorio(Directorio actual, String nombre) {
    if (actual.getNombre().equals(nombre)) return actual;
    for (int i = 0; i < actual.getSubdirectorios().getLength(); i++) {
        Directorio encontrado = buscarDirectorio(actual.getSubdirectorios().get(i), nombre);
        if (encontrado != null) return encontrado;
    }
    return null;
}

// Directorio.java — liberación recursiva de memoria al eliminar
private void liberarRecursos(Directorio dir, MemoryManager mm) {
    for (int i = 0; i < dir.getArchivos().getLength(); i++) {
        mm.liberarBloques(dir.getArchivos().get(i).getBloquesAsignados());
    }
    for (int i = 0; i < dir.getSubdirectorios().getLength(); i++) {
        liberarRecursos(dir.getSubdirectorios().get(i), mm); // recursivo
    }
}
```

---

## Persistencia con Gson — el desafío de las estructuras propias

El mayor reto técnico fue que Gson no sabe serializar `Lista<T>` ni `Queue<T>` porque son estructuras propias, no de `java.util`. La solución fue escribir **TypeAdapters custom** que implementan `JsonSerializer` y `JsonDeserializer`:

```java
// ListaAdapter.java — serializa Lista<T> a JsonArray con metadato de tipo
@Override
public JsonElement serialize(Lista<T> lista, Type type, JsonSerializationContext ctx) {
    JsonArray array = new JsonArray();
    for (int i = 0; i < lista.getLength(); i++) {
        T item = lista.get(i);
        JsonObject obj = new JsonObject();
        obj.addProperty("__type", item.getClass().getName()); // guarda el tipo real
        obj.add("data", ctx.serialize(item));
        array.add(obj);
    }
    return array;
}
```

Al cargar, los bloques deserializados son objetos nuevos en memoria y los punteros `siguienteBloque` se pierden. Para reconstruirlos se usa `siguienteId` (que sí persiste como entero) para reenlazar la cadena:

```java
// MemoryManager.java — reconstrucción de punteros post-deserialización
public void reconstruirSiguienteBloques() {
    for (int i = 0; i < todosLosBloques.getLength(); i++) {
        Bloque b = todosLosBloques.get(i);
        if (b.getSiguienteId() != -1) {
            b.setSiguienteBloque(findBloqueById(b.getSiguienteId()));
        }
    }
}
```

---

## Logger de auditoría

Cada operación queda registrada en `log.txt` con timestamp, usuario y acción:

```
[2025-03-20 17:22:26] Usuario: Administrador - Acción: Creó un archivo
[2025-03-20 17:22:34] Usuario: Administrador - Acción: Creó un directorio
[2025-03-20 17:23:05] Usuario: Administrador - Acción: Eliminó el directorio: ASA
[2025-03-20 17:23:21] Usuario: Usuario       - Acción: Cambió a Modo Usuario.
[2025-03-20 17:23:25] Usuario: Usuario       - Acción: Guardó el estado del sistema.
```

---

## Modos de usuario

| Modo | Permisos |
|------|---------|
| **Administrador** | Crear, eliminar y renombrar directorios y archivos; generar datos aleatorios; guardar/cargar estado |
| **Usuario** | Solo lectura; puede cargar y guardar estado pero no modificar la estructura |

---

## Cómo ejecutar

**Requisitos:** JDK 8+ · NetBeans IDE

```bash
git clone https://github.com/GianfrancoMongiell0/SimuladorVirtualSO
```

1. Abre **NetBeans → Open Project** y selecciona `ProyectoSO2/`
2. Run → `Main.java`
3. El sistema carga el estado desde JSON si existe, o inicia uno nuevo

---

## Autores

**Gianfranco Mongiello** · **Alejandra Carrera**  
Materia: Sistemas Operativos II — Universidad Metropolitana (UNIMET), Caracas 🇻🇪
