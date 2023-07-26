# Web-applications-for-the-warehouse
веб-приложение, с помощью которого склад может учитывать и автоматизировать учет товаров на складе интернет-магазина носков. 

#С помощью программы работник склада имеет возможность:

- учитывать приход и отпуск носков;
- узнать общее количество носков определенного цвета и состава в данный момент времени;
- дополнительно иметь возможность парсить (читать и преобразовывать данные) файлы с данными по товару.

Внешний интерфейс приложения представлен в виде REST API.

- POST /api/socks регистрирует приход товара на склад.
- PUT /api/socks регистрирует отпуск носков со склада. Здесь параметры и результаты аналогичные, но общее количество носков указанного цвета и состава не увеличивается, а уменьшается.
- GET /api/socks возвращает общее количество носков на складе, соответствующих переданным в параметрах критериям запроса. В данном методе количество носков остается неизменным, так как мы запрашиваем информацию о товарах на складе.
- DELETE /api/socks регистрирует списание испорченных (бракованных) носков.

 # Использован следующий стек технологий:
 - Веб-приложение выполнено в виде RESTful-сервиса
 - Java11
 - SpringBoot
 - Swagger
 - Lombok