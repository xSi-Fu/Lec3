abstract class Room {
    private int roomNumber;
    private int maxPeople;
    private int pricePerNight;
    private boolean isBooked;

    public Room(int roomNumber, int maxPeople, int pricePerNight) {
        this.roomNumber = roomNumber;
        this.maxPeople = maxPeople;
        this.pricePerNight = pricePerNight;
        this.isBooked = false;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    public boolean isBooked() {
        return isBooked;
    }

    protected void setBooked(boolean booked) {
        isBooked = booked;
    }
}

class EconomyRoom extends Room {
    public EconomyRoom(int roomNumber) {
        super(roomNumber, 2, 1000); // Максимум 2 человека, цена 1000
    }
}

abstract class ProRoom extends Room {
    public ProRoom(int roomNumber, int maxPeople, int pricePerNight) {
        super(roomNumber, maxPeople, pricePerNight);
    }
}

class StandardRoom extends EconomyRoom {
    public StandardRoom(int roomNumber) {
        super(roomNumber);
    }
}

class LuxRoom extends ProRoom {
    public LuxRoom(int roomNumber) {
        super(roomNumber, 3, 3000); // Максимум 3 человека, цена 3000
    }
}

class UltraLuxRoom extends LuxRoom {
    public UltraLuxRoom(int roomNumber) {
        super(roomNumber);
    }
}

class RoomAlreadyBookedException extends RuntimeException {
    public RoomAlreadyBookedException(String message) {
        super(message);
    }
}

interface RoomService<T extends Room> {
    void clean(T room);
    void reserve(T room);
    void free(T room);
}

class SimpleRoomService<T extends Room> implements RoomService<T> {
    @Override
    public void clean(T room) {
        System.out.println("Комната " + room.getRoomNumber() + " убрана");
    }

    @Override
    public void reserve(T room) {
        if (room.isBooked()) {
            throw new RoomAlreadyBookedException("Комната " + room.getRoomNumber() + " уже забронирована");
        }
        room.setBooked(true);
        System.out.println("Комната " + room.getRoomNumber() + " забронирована");
    }

    @Override
    public void free(T room) {
        room.setBooked(false);
        System.out.println("Комната " + room.getRoomNumber() + " освобождена");
    }
}
