```markdown
# 📋 JavaFX To-Do List App

Aplikasi manajemen tugas (to-do list) sederhana berbasis **JavaFX**. Aplikasi ini memungkinkan pengguna untuk menambahkan tugas berdasarkan **prioritas** dan **deadline**, menandai tugas sebagai selesai, serta menghapus tugas dari daftar. Program mendukung pemisahan tugas berdasarkan prioritas: **High**, **Medium**, dan **Low**.

---

## 🛠️ Fitur Utama

- ✅ Tambah tugas baru dengan deskripsi dan prioritas.
- 🗓️ (Opsional) Tambahkan **deadline** untuk tugas.
- ⛔ Tidak bisa memilih tanggal deadline yang telah berlalu — hanya **hari ini atau setelahnya**.
- ☑️ Checklist untuk menandai tugas yang sudah selesai.
- 🗑️ Hapus tugas yang dipilih.
- 📊 Tugas dikelompokkan berdasarkan prioritas ke dalam tabel berbeda.

---

## 📁 Struktur Proyek

src/
└── main/
    ├── java/
    │   ├── module-info.java
    │   └── com/app/
    │       ├── App.java                 # Entry point aplikasi
    │       ├── DeadlineTask.java        # Task dengan deadline
    │       ├── SimpleTask.java          # Task tanpa deadline
    │       ├── Task.java                # Kelas abstrak Task
    │       ├── PrimaryController.java   # Controller tampilan 
    │       │                             utama
    │       └── SecondaryController.java # Controller form 
    │                                      tambah tugas
    └── resources/
        └── com/app/
            ├── primary.fxml             # Tampilan utama   
            └── secondary.fxml           # Tampilan form tambah  
                                           tugas

---

## 🔧 Requirements

- Java 11 atau lebih baru
- JavaFX SDK (sesuaikan dengan IDE yang digunakan)

---

## 📸 Tampilan

- **primary.fxml**: Daftar tugas berdasarkan prioritas
- **secondary.fxml**: Form tambah tugas baru

---

## 📌 Catatan

- Semua tugas disimpan di memori (tidak ada database atau file eksternal).
- Tidak mendukung penyimpanan data setelah aplikasi ditutup.

---

## 📤 Kontribusi

Untuk pengembangan lebih lanjut, beberapa fitur yang bisa ditambahkan:

- 💾 Penyimpanan ke file (JSON/XML)
- 📅 Sorting berdasarkan tanggal deadline
- ✏️ Fitur edit tugas
- 🌙 Tema gelap / light mode

Silakan fork dan modifikasi sesuai kebutuhan Anda!