import ru.nurguru.data.model.GuestEntity
import ru.nurguru.data.model.OrderEntity
import ru.nurguru.domain.model.Discount
import ru.nurguru.domain.model.Guest
import ru.nurguru.domain.model.Order
import ru.nurguru.domain.model.Status

fun Guest.map() = GuestEntity(
    id = id,
    name = name,
    number = number,
    visits = visits,
    moneySpent = moneySpent,
    statusStr = status.name,
    discountStr = discount.name
)

fun GuestEntity.map() = Guest(
    id = id,
    name = name,
    number = number,
    visits = visits,
    moneySpent = moneySpent,
    status = Status.fromString(statusStr),
    discount = Discount.fromString(discountStr)
)

fun OrderEntity.map() = Order(
    id = id,
    guestId = guestId,
    sum = sum,
)

fun Order.map() = OrderEntity(
    id = id,
    guestId = guestId,
    sum = sum,
)


