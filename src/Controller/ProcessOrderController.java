package Controller;

import Interface.Customer.*;

public abstract class ProcessOrderController extends OrderController implements DisplayMenu, MakePayment, TrackOrderStatus, CollectFood, PrintReceipt {
}
