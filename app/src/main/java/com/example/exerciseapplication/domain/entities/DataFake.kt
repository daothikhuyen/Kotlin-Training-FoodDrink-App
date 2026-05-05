package com.example.exerciseapplication.domain.entities

object FakeData {
    fun getFoodList(): MutableList<MenuItem> {
        return mutableListOf(
            MenuItem(1, "Cá Hấp", 356000, "food", false, "Cá tươi hấp gừng, giữ vị ngọt tự nhiên."),
            MenuItem(2, "Phở Tái", 659000, "food", false, "Phở bò với thịt tái mềm, nước dùng đậm đà."),
            MenuItem(3, "Cua Hấp", 10000, "food", false, "Cua hấp sả thơm, thịt chắc và ngọt."),
            MenuItem(4, "Cơm Chiên", 456000, "food", false, "Cơm chiên vàng giòn, kết hợp trứng và rau."),
            MenuItem(5, "Gà Nướng", 123000, "food", false, "Gà nướng da giòn, thịt mềm."),
            MenuItem(6, "Bò Bít Tết", 68000, "food", false, "Bò áp chảo mềm, ăn kèm sốt tiêu."),
            MenuItem(7, "Bún Bò Huế", 45000, "food", false, "Bún bò cay nhẹ, đậm vị Huế."),
            MenuItem(8, "Mì Xào Hải Sản", 78000, "food", false, "Mì xào tôm mực, rau tươi."),
            MenuItem(9, "Lẩu Thái", 250000, "food", false, "Lẩu chua cay, nhiều topping."),
            MenuItem(10, "Gỏi Cuốn", 30000, "food", false, "Cuốn tôm thịt, nước chấm đậm đà."),
            MenuItem(11, "Chả Giò", 55000, "food", false, "Chả giò chiên giòn, nhân thơm."),
            MenuItem(12, "Bánh Xèo", 60000, "food", false, "Bánh xèo giòn rụm, nhân tôm thịt."),
            MenuItem(13, "Bún Chả", 70000, "food", false, "Bún chả với thịt nướng thơm."),
            MenuItem(14, "Hủ Tiếu Nam Vang", 65000, "food", false, "Hủ tiếu nước trong, topping đa dạng."),
            MenuItem(15, "Cơm Tấm Sườn", 50000, "food", false, "Cơm tấm sườn nướng, bì chả."),
            MenuItem(16, "Canh Chua Cá", 90000, "food", false, "Canh chua thanh mát."),
            MenuItem(17, "Ốc Hương Xào Bơ Tỏi", 120000, "food", false, "Ốc xào thơm béo, đậm vị."),
            MenuItem(18, "Tôm Nướng Muối Ớt", 150000, "food", false, "Tôm nướng cay nhẹ, thịt chắc."),
            MenuItem(19, "Hủ Tiếu Nam Vang", 65000, "food", false, "Hủ tiếu truyền thống.")
        )
    }

    fun getDrinkList(): MutableList<MenuItem> {
        return mutableListOf(
            MenuItem(101, "Nước Cam", 356000, "drink", false, "Nước cam tươi, giàu vitamin C."),
            MenuItem(102, "Nước Dâu", 43000, "drink", false, "Nước dâu chua ngọt."),
            MenuItem(112, "Nước Ép Dưa Hấu", 35000, "drink", false, "drink mát lạnh."),

            MenuItem(103, "Trà Chanh", 78000, "drink", false, "Trà chanh giải khát."),
            MenuItem(104, "Trà Đào", 15000, "drink", false, "Trà đào thơm ngon."),
            MenuItem(111, "Trà Sữa Trân Châu", 45000, "drink", false, "Trà sữa béo, trân châu dai."),

            MenuItem(105, "Cà Phê", 37000, "drink", false, "Cà phê nguyên chất."),
            MenuItem(106, "Cà Phê Đen", 38000, "drink", false, "Cà phê đậm vị."),
            MenuItem(107, "Cà Phê Sữa", 40000, "drink", false, "Cà phê sữa béo nhẹ."),
            MenuItem(108, "Bạc Xỉu", 42000, "drink", false, "Sữa nhiều, cà phê ít."),

            MenuItem(109, "Sinh Tố Xoài", 50000, "drink", false, "Sinh tố xoài mịn."),
            MenuItem(110, "Sinh Tố Dâu", 52000, "drink", false, "Sinh tố dâu tươi.")
        )
    }
}