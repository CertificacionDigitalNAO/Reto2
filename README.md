![Logo DigitalNAO x Tecmilenio.](./NAO_Tecmilenio.svg)

# Reto 2: Comandos para servidores y bases de datos
Se presenta a continuación, la documentación de [***Google Scholar API***](https://serpapi.com/google-scholar-api) a través de ***SerpAPI***. Además, se incluye un proyecto en Java con conexión a una base de datos. 

## Google Scholar API
Esta API permite extraer resultados SERP de una consulta de búsqueda de Google Scholar y se puede acceder a la API a través del siguiente endpoint: `/search?engine=google_scholar`.

Un usuario puede consultar lo siguiente: `https://serpapi.com/search?engine=google_scholar` utilizando una petición <span style="color:green">**`GET`**</span>. 

## Parámetros de la API
### :mag: Consulta de búsqueda
- `q` | <span style="color:red">**Required**</span> |
  - El parámetro define la consulta que desea buscar. También se puede utilizar ayudantes en la consulta como: `author:` o `source:`.
  - El uso del parámetro `cites` hace `q` opcional. El uso de `cites` junto con `q` activa la búsqueda dentro de los artículos citados.
  - El uso de `cluster` junto con los parámetros `q` y `cites` está prohibido. Utilice únicamente el parámetro `cluster`.

### :mortar_board: Parámetros avanzados de Google Scholar
| Parámetro  |    Estado   | Descripción  |
|------------|-------------|--------------|
| `cites`    | **Optional** | El parámetro define el ID único de un artículo para activar las búsquedas de **Cited By**. El uso de `cites` mostrará una lista de documentos citados en Google Scholar. Valor de ejemplo: `cites=1275980731835430123`. El uso de los parámetros `cites` y `q` activa la búsqueda dentro de los artículos citados. |
| `as_ylo`   | **Optional** | El parámetro define el año a partir del cual desea que se incluyan los resultados. (Por ejemplo, si establece el parámetro `as_ylo` en el año `2018`, se omitirán los resultados anteriores a ese año). Este parámetro puede combinarse con el parámetro `as_yhi`. |
| `as_yhi`   | **Optional** | El parámetro define el año hasta el que desea que se incluyan los resultados. (Por ejemplo, si establece el parámetro `as_yhi` en el año `2018`, se omitirán los resultados posteriores a ese año). Este parámetro puede combinarse con el parámetro `as_ylo`. |
| `scisbd`   | **Optional** | El parámetro define los artículos añadidos en el último año, ordenados por fecha. Puede establecerse en `1` para incluir sólo los resúmenes, o en `2` para incluirlo todo. El valor por defecto es `0` lo que significa que los artículos se ordenan por relevancia. |
| `cluster`  | **Optional** | El parámetro define el ID único de un artículo para activar las búsquedas de **All Versions**. Valor de ejemplo: `cluster=1275980731835430123`. El uso de `cluster` junto con los parámetros `q` y `cites` está prohibido. Utilice únicamente el parámetro `cluster`. |


### :compass: Localización
| Parámetro  |    Estado   | Descripción  |
|------------|-------------|--------------|
| `hl`    | **Optional** | El parámetro define el idioma que se utilizará para la búsqueda en Google Scholar. Es un código de idioma de dos letras. (por ejemplo, `en` para Inglés, `es` para Español o `fr` para Francés). Visita la página [Google language page](https://serpapi.com/google-languages) para consultar la lista completa de idiomas de Google compatibles. |
| `lr`    | **Optional** | El parámetro define uno o varios idiomas para limitar la búsqueda. Utiliza `lang_{two-letter language code}` para especificar los idiomas y `\` como delimitador. Por ejemplo, `lang_fr\lang_de` sólo buscará en páginas francesas y alemanas. Visita la página [Google language page](https://serpapi.com/google-languages) para consultar la lista completa de idiomas de Google compatibles. |

### :page_with_curl: Paginación
| Parámetro  |    Estado   | Descripción  |
|------------|-------------|--------------|
| `start`    | **Optional** | El parámetro define el desplazamiento del resultado. Omite el número dado de resultados. Se utiliza para la paginación. (por ejemplo, `0` (por defecto) es la primera página de resultados, `10` es la 2ª página de resultados, `20` es la 3ª página de resultados, etc.). |
| `num`    | **Optional** | El parámetro define el número máximo de resultados a devolver, limitado a 20. (por ejemplo, `10` (por defecto) devuelve 10 resultados, `20` devuelve 20 resultados). |

### :gear: Filtros avanzados
| Parámetro  |    Estado   | Descripción  |
|------------|-------------|--------------|
| `safe`    | **Optional** | Este parámetro define el nivel de filtrado de contenidos para adultos. Puede configurarse como `active` o `off`, por defecto Google difuminará el contenido explícito. |
| `filter`  | **Optional** | El parámetro define si los filtros para _'Resultados similares'_ y _'Resultados omitidos'_ están activados o desactivados. Se puede establecer en `1` (por defecto) para activar estos filtros, o en `0` para desactivarlos. |
| `as_vis`  | **Optional** | El parámetro define si desea incluir las citas o no. Se puede establecer en `1` para excluir estos resultados, o en `0` (por defecto) para incluirlos. |
| `as_rr`   | **Optional** | El parámetro define si desea mostrar sólo artículos de revisión o no (estos artículos consisten en revisiones de temas, o discuten las obras o autores que ha buscado). Se puede establecer en `1` para activar este filtro, o en `0` (por defecto) para mostrar todos los resultados. |

### :hammer_and_wrench: Parámetros Serpapi
| Parámetro  |    Estado   | Descripción  |
|------------|-------------|--------------|
| `engine`    | **Required** | Establezca el parámetro `google_scholar` para utilizar el motor de la API Google Scholar. |
| `no_cache`  | **Optional** | El parámetro obligará a SerpApi a obtener los resultados de Google Scholar incluso si ya existe una versión en caché. Sólo se sirve una versión en caché si la consulta y todos los parámetros son exactamente iguales. La caché caduca al cabo de 1 hora. Las búsquedas en caché son gratuitas y no se tienen en cuenta para las búsquedas mensuales. Puede establecerse en `false` (por defecto) para permitir resultados de la caché, o en `true` para no permitir resultados de la caché. Los parámetros `no_cache` y `async` no deben utilizarse juntos. |
| `async`  | **Optional** | El parámetro define la forma en que desea enviar su búsqueda a SerpApi. Se puede establecer en `false` (por defecto) para abrir una conexión HTTP y mantenerla abierta hasta que obtengas los resultados de la búsqueda, o en `true` para simplemente enviar la búsqueda a SerpApi y recuperarlos más tarde. En este caso, tendrá que utilizar [Searches Archive API](https://serpapi.com/search-archive-api) para recuperar tus resultados. Los parámetros `async` y `no_cache` no deben utilizarse juntos. `async` no debe utilizarse en cuentas con [Ludicrous Speed](https://serpapi.com/plan) activada. |
| `api_key`   | **Required** | El parámetro define la clave privada SerpApi a utilizar. |
| `output`   | **Optional** | El parámetro define la salida final que desea. Puede establecerse como json (por defecto) para obtener un `JSON` estructurado de los resultados, o `html` para obtener el html sin procesar recuperado. |

## :bar_chart: API Results
| Resultados JSON  |    Resultados HTML   |
|------------------|----------------------|
| La salida JSON incluye datos estructurados para los resultados orgánicos. El estado de una búsqueda es accesible a través de `search_metadata.status`. Fluye de la siguiente manera `Procesamiento -> Éxito \\ Error`. Si una búsqueda ha fallado, `error` contendrá un mensaje de error. `search_metadata.id` es el ID de búsqueda dentro de SerpApi.  |    La salida HTML es útil para depurar los resultados JSON o para admitir funciones que SerpApi aún no admite. La salida HTML te proporciona los resultados HTML sin procesar de Google.   |

## Ejemplos e implementación de la API
Código para integrar con __Java__:
```
Map<String, String> parameter = new HashMap<>();

parameter.put("engine", "google_scholar");
parameter.put("q", "biology");
parameter.put("api_key", "fef472c7067e863a65493d7e24302a04c6d4c1e7f2fee51e0ef3da5410eced42");

GoogleSearch search = new GoogleSearch(parameter);

try {
  JsonObject results = search.getJson();
  var organic_results = results.get("organic_results");
} catch (SerpApiSearchException ex) {
  System.out.println("Exception:");
  System.out.println(ex.toString());
}
```

Ejemplo con `q: biology`:
```
GET
https://serpapi.com/search.json?engine=google_scholar&q=biology
```

## Configuración del Proyecto

### Requisitos
- Java 17+
- Maven
- MySQL

### Variables de Entorno
Crea un archivo `.env` en la raíz del proyecto basado en `.env.example` y define las siguientes variables:
