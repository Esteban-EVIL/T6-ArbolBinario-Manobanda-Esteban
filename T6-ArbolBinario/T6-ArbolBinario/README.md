# T6-ArbolBinario-ApellidoNombre

**Asignatura:** Estructura de Datos · **Nivel:** Tercero – Ingeniería de Software
**Autor:** Nombre Apellido

## 1. Problema
La Universidad necesita representar la organización de sus dependencias académicas mediante un
árbol binario. Cada nodo guarda el nombre de una dependencia y tiene como máximo dos hijos.

## 2. Estructura del árbol
```
UNIVERSIDAD
├── [I] FISEI
│   ├── [I] SOFTWARE
│   │   ├── [I] NIVEL1
│   │   └── [D] NIVEL3
│   └── [D] TI
└── [D] DIDE
    ├── [I] INVEST.
    └── [D] VINCULACIÓN
```
9 nodos · 5 hojas · altura 3 (en aristas).

## 3. Organización del proyecto
```
src/
 ├── modelo/Nodo.java
 ├── negocio/ArbolBinario.java
 └── app/Main.java
```
Conteo de nodos, conteo de hojas y altura implementados con **recursividad**.
No se usan TreeSet, TreeMap, PriorityQueue, NavigableSet ni NavigableMap.

## 4. Instrucciones de ejecución
```bash
cd src
javac -encoding UTF-8 modelo/Nodo.java negocio/ArbolBinario.java app/Main.java
java app.Main
```
Requiere JDK 17 o superior (usa `switch` con flechas).

## 5. Resultados
| Caso de prueba | Esperado | Obtenido |
|---|---|---|
| Árbol vacío | 0 nodos, altura -1 | _completar_ |
| Un solo nodo | 1 hoja, altura 0 | _completar_ |
| Árbol inicial | 9 nodos, 5 hojas, altura 3 | _completar_ |
| Insertar en posición ocupada | Se rechaza | _completar_ |
| Grado de FISEI y SOFTWARE | 2 y 2 | _completar_ |

### Capturas
_Agregar capturas de la ejecución y de los casos de prueba (opción 8 del menú)._
