//package ru.nurguru.data.source
//
//import ru.nurguru.data.model.OrderEntity
//
//class OrdersDataSourceImpl(
//    private val parser: CsvParser,
//    private val handler: FileHandler,
//    private val threadLoading: MultiThreading
//) : OrdersDataSource {
//
//    override fun getOrdersList(): List<OrderEntity> {
//        val ordersLines = threadLoading.fileLoading(Constants.ORDERS_FILE_PATH)
//        val parsedOrders = parser.parse(ordersLines, Constants.DELIMITER_SLASH)
//        return parsedOrders
//            .map {
//                OrderEntity(
//                    id = it[0].trim().toInt(),
//                    guestId = it[1].toInt(),
//                    sum = it[2].trim().toInt(),
//                )
//            }.toList()
//    }
//
//    override fun saveOrdersList(newOrdersList: List<OrderEntity>) {
//        val text = newOrdersList.joinToString(Constants.SEPARATOR, "", Constants.POSTFIX)
//        handler.saveText(Constants.ORDERS_FILE_PATH, text)
//    }
//}