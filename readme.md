Ejercicio: Procesar formulario con MVC

Diseñar una aplicación web que ofrezca una página web que posibilite realizar las siguientes operaciones:

1. Botón "info" que pulsándolo nos muestre una página de información general. En esa página habrá un enlace denominado "volver" que permitirá regresar a la página de inicio.

2. Un botón "login" que al pulsarlo muestra un formulario para identificarse. El formulario se compone de dos cuadros de texto, uno para el login y otro para la clave. Estos datos estarán almacenados en una base de datos que se accederá mediante un datasource. Una vez rellenados los campos se podrá intentar logear. Si el logeo es correcto, se mostrará una página con los datos introducidos y un botón "volver". Si hay errores, se informará de esta circunstancia y se mostrará un botón para "volver".

2. Puede incorporarse en la aplicación algún mecanismo para registrar nuevos usuarios, bien incorporando un botón para tal efecto en la página del formulario o bien en la página principal.

Datasource:

                <datasource jta="true" jndi-name="java:jboss/datasources/dsprocesaformulario" pool-name="dsprocesaformulario" enabled="true" use-java-context="true" use-ccm="true">
                    <connection-url>jdbc:mysql://localhost:3306/procesaformulario</connection-url>
                    <driver>mysql5</driver>
                    <security>
                        <user-name>root</user-name>
                        <password>mysql</password>
                    </security>
                </datasource>
