public class Main {
    public static void main(String[] args) {
        RoomService<Room> service = new SimpleRoomService<>();

        StandardRoom standard = new StandardRoom(101);
        LuxRoom lux = new LuxRoom(201);
        UltraLuxRoom ultraLux = new UltraLuxRoom(301);

        testRoomService(service, standard);
        testRoomService(service, lux);
        testRoomService(service, ultraLux);

        try {
            service.reserve(standard);
        } catch (RoomAlreadyBookedException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void testRoomService(RoomService<Room> service, Room room) {
        System.out.println("\nТестируем комнату №" + room.getRoomNumber() + " (" + room.getClass().getSimpleName() + ")");
        service.clean(room);
        service.reserve(room);
        service.free(room);
    }
}