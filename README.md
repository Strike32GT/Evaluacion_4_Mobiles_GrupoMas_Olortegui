# TaskManager Pro

Aplicación móvil para gestión de tareas personales, desarrollada con:

- **Kotlin**
- **Jetpack Compose**
- **Firebase (Auth + Firestore)**

---

## Tecnologías usadas

| Tecnología        | Descripción                              |
|------------------|-------------------------------------------|
| ![Kotlin](https://upload.wikimedia.org/wikipedia/commons/7/74/Kotlin_Icon.png) | Lenguaje principal de la app. |
| ![Jetpack Compose](https://developer.android.com/images/brand/Android_Robot.png) | Framework de UI declarativa para Android. |
| ![Firebase](https://www.gstatic.com/mobilesdk/160503_mobilesdk/logo/2x/firebase_28dp.png) | Backend para autenticación y base de datos. |

*(Si los logos no se ven en tu entorno, solo se mostrarán como texto alternativo.)*

---

## Descripción de la aplicación

**TaskManager Pro** es una app móvil que permite a un usuario:

- **Registrarse e iniciar sesión** usando **Firebase Authentication**.  
- **Crear tareas** con:
  - Título  
  - Prioridad  
  - Fecha límite  
  - Estado  
- **Ver tareas en tiempo real** desde **Cloud Firestore**.  
- **Actualizar** cualquier campo de una tarea.  
- **Marcar tareas como completadas**.  
- **Eliminar tareas** (CRUD completo).

Está pensada como una herramienta sencilla y directa para organizar actividades personales o académicas.

---

## Patrón de diseño y estructura

La app sigue una arquitectura basada en **MVVM** y separación por capas:

- **ui/**
  - `screens/auth`  
    - `LoginScreen.kt`  
    - `RegisterScreen.kt`  
  - `screens/tasks`  
    - `TaskListScreen.kt`  
    - `TaskFormScreen.kt`  
  - `components`  
    - Componentes reutilizables (por ejemplo, `TaskCard`, botones, textfields).  
  - `theme`  
    - Colores, tipografías y estilos de Jetpack Compose.  
  - `navigation`  
    - `AppNavHost.kt` para la navegación entre pantallas.

- **presentation/**
  - ViewModels para manejar el estado de autenticación y tareas, y conectar la UI con los repositorios (Firebase).

- **data/**
  - `model`: modelos de datos que se guardan en Firestore.  
  - `remote/auth`: integración con **Firebase Authentication**.  
  - `remote/firestore`: integración con **Cloud Firestore**.  
  - `repository`: clases que exponen métodos de alto nivel para la UI (CRUD de tareas, login, registro, etc.).

---

## Funcionalidades principales

- **Autenticación**
  - Pantalla de Login (email + contraseña).
  - Pantalla de Registro (email + contraseña + confirmación).

- **Gestión de tareas**
  - Lista de tareas del usuario actual.
  - Creación y edición de tareas con:
    - Título
    - Prioridad (Alta, Media, Baja)
    - Fecha límite
    - Estado (Pendiente, En progreso, Completada)
  - Marcar tarea como completada.
  - Eliminación de tareas.

---

## Objetivo del proyecto

Este proyecto se desarrolla como parte del curso **Programación de Aplicaciones Móviles** para aplicar:

- Diseño de interfaces con **Jetpack Compose**.  
- Uso de patrón **MVVM**.  
- Integración de aplicaciones Android con **Firebase** (Auth + Firestore).

---

## Equipo de trabajo

- **Miembros del grupo:**
  - Fernando Mas  
  - Olortegui Padilla  

- **Institución:** TECSUP  
- **Ciclo / Grupo:** 4C24D TD  
- **Curso:** Programación de Aplicaciones Móviles  
- **Docente:** Juan León