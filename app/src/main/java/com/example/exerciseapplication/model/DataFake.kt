package com.example.exerciseapplication.model

object FakeData {
    fun getFoodList(): List<MenuFoodItem> {
        return listOf(
            MenuFoodItem(1, "Cá Hấp", 356000, "Đồ ăn", false, "Cá tươi hấp gừng, giữ vị ngọt tự nhiên."),
            MenuFoodItem(2, "Phở Tái", 659000, "Đồ ăn", false, "Phở bò với thịt tái mềm, nước dùng đậm đà."),
            MenuFoodItem(3, "Cua Hấp", 10000, "Đồ ăn", false, "Cua hấp sả thơm, thịt chắc và ngọt."),
            MenuFoodItem(4, "Cơm Chiên", 456000, "Đồ ăn", false, "Cơm chiên vàng giòn, kết hợp trứng và rau."),
            MenuFoodItem(5, "Gà Nướng", 123000, "Đồ ăn", false, "Gà nướng da giòn, thịt mềm."),
            MenuFoodItem(6, "Bò Bít Tết", 68000, "Đồ ăn", false, "Bò áp chảo mềm, ăn kèm sốt tiêu."),
            MenuFoodItem(7, "Bún Bò Huế", 45000, "Đồ ăn", false, "Bún bò cay nhẹ, đậm vị Huế."),
            MenuFoodItem(8, "Mì Xào Hải Sản", 78000, "Đồ ăn", false, "Mì xào tôm mực, rau tươi."),
            MenuFoodItem(9, "Lẩu Thái", 250000, "Đồ ăn", false, "Lẩu chua cay, nhiều topping."),
            MenuFoodItem(10, "Gỏi Cuốn", 30000, "Đồ ăn", false, "Cuốn tôm thịt, nước chấm đậm đà."),
            MenuFoodItem(11, "Chả Giò", 55000, "Đồ ăn", false, "Chả giò chiên giòn, nhân thơm."),
            MenuFoodItem(12, "Bánh Xèo", 60000, "Đồ ăn", false, "Bánh xèo giòn rụm, nhân tôm thịt."),
            MenuFoodItem(13, "Bún Chả", 70000, "Đồ ăn", false, "Bún chả với thịt nướng thơm."),
            MenuFoodItem(14, "Hủ Tiếu Nam Vang", 65000, "Đồ ăn", false, "Hủ tiếu nước trong, topping đa dạng."),
            MenuFoodItem(15, "Cơm Tấm Sườn", 50000, "Đồ ăn", false, "Cơm tấm sườn nướng, bì chả."),
            MenuFoodItem(16, "Canh Chua Cá", 90000, "Đồ ăn", false, "Canh chua thanh mát."),
            MenuFoodItem(17, "Ốc Hương Xào Bơ Tỏi", 120000, "Đồ ăn", false, "Ốc xào thơm béo, đậm vị."),
            MenuFoodItem(18, "Tôm Nướng Muối Ớt", 150000, "Đồ ăn", false, "Tôm nướng cay nhẹ, thịt chắc."),
            MenuFoodItem(19, "Hủ Tiếu Nam Vang", 65000, "Đồ ăn", false, "Hủ tiếu truyền thống.")
        )
    }

    fun getDrinkList(): List<MenuDrinkItem> {
        return listOf(
            MenuDrinkItem(101, "Nước Cam", 356000, "Nước ép", false, "Nước cam tươi, giàu vitamin C."),
            MenuDrinkItem(102, "Nước Dâu", 43000, "Nước ép", false, "Nước dâu chua ngọt."),
            MenuDrinkItem(112, "Nước Ép Dưa Hấu", 35000, "Nước ép", false, "Nước ép mát lạnh."),

            MenuDrinkItem(103, "Trà Chanh", 78000, "Trà", false, "Trà chanh giải khát."),
            MenuDrinkItem(104, "Trà Đào", 15000, "Trà", false, "Trà đào thơm ngon."),
            MenuDrinkItem(111, "Trà Sữa Trân Châu", 45000, "Trà", false, "Trà sữa béo, trân châu dai."),

            MenuDrinkItem(105, "Cà Phê", 37000, "Cà phê", false, "Cà phê nguyên chất."),
            MenuDrinkItem(106, "Cà Phê Đen", 38000, "Cà phê", false, "Cà phê đậm vị."),
            MenuDrinkItem(107, "Cà Phê Sữa", 40000, "Cà phê", false, "Cà phê sữa béo nhẹ."),
            MenuDrinkItem(108, "Bạc Xỉu", 42000, "Cà phê", false, "Sữa nhiều, cà phê ít."),

            MenuDrinkItem(109, "Sinh Tố Xoài", 50000, "Sinh tố", false, "Sinh tố xoài mịn."),
            MenuDrinkItem(110, "Sinh Tố Dâu", 52000, "Sinh tố", false, "Sinh tố dâu tươi.")
        )
    }
}