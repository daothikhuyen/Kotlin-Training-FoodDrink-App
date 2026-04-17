# Exercise Application 
- **App: FoodDrink Application**
---
## Chức năng chính
- Hiển thị danh sách **món ăn**
- Hiển thị danh sách **đồ uống**
- Chọn ngẫu nhiên một món khi mở màn hình
- Nút **Next** để chuyển sang món / nước tiếp theo
- Nút **Previous** để quay lại món / nước trước đó
- Chuyển tab giữa **Food** và **Drink** bằng:
    - `BottomNavigationView`
    - `ViewPager2`

---
## Công nghệ sử dụng

- **Kotlin**
- **Android Studio**
- **ViewBinding**
- **Fragment**
- **ViewPager2**
- **BottomNavigationView**

---

## Cấu trúc project

```bash
com.example.exerciseapplication
│
├── HomeActivity.kt
│
├── ui
│   ├── adapter
│   │   └── HomeAdapter.kt
│   │
│   ├── drink
│   │   └── DrinkFragment.kt
│   │
│   └── fastfood
│       └── FoodFragment.kt
│
├── res
│   ├── layout
│   │   ├── activity_home.xml
│   │   └── fragment_item.xml
│   │
│   ├── drawable
│   │   └── bg_border_card.xml
│   │
│   └── values
│       └── strings.xml