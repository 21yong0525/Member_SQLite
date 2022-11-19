# Member_SQLite

### 실행화면

https://user-images.githubusercontent.com/115531869/202854426-0ed8ac11-ff95-45f9-8580-fd5d1b26fad6.mp4

| 기능 |
| --- |
| insert : 회원가입 완료시 table 에 유저 정보 저장 <div> → selectCheckID : rawQuery (selcet ID 중복 검사) <div> selectLogin : rawQuery (selcet 값 검사 후 로그인) <div> selectID : rawQuery (selcet 해당 id 모든 정보) |
| ![회원가입0](https://user-images.githubusercontent.com/115531869/202854720-4c8c717a-8727-4841-89f6-1d0f7458ad5c.png) |
| selectList : rawQuery (select 모든 유저 정보) |
| ![리스트0](https://user-images.githubusercontent.com/115531869/202854775-6db6cc1c-1cd5-4917-8045-472e6d6bbeb0.png) |
| delete : execSQL (delete 해당 ID 삭제) |
| ![삭제0](https://user-images.githubusercontent.com/115531869/202854781-18b3098a-6749-468c-a9c1-a101bc799844.png) | 
| update : execSQL (update 해당 ID 수정) |
| ![수정0](https://user-images.githubusercontent.com/115531869/202854772-2d11d861-7773-487f-882e-54d48e815c80.png) |
