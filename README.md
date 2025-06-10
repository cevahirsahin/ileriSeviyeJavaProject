# 💼 İleri Seviye Java Project

Bu proje, **İleri Seviye Java** dersi kapsamında sınıfça ortak olarak geliştirilen bir yazılım geliştirme çalışmasıdır. Spring Boot çatısı altında inşa edilmiş olan bu uygulama, **katmanlı mimari** prensiplerine göre tasarlanmıştır. Temel olarak veri erişimi, servis katmanı, controller (API) katmanı ve testler olmak üzere modern bir Java back-end yapısı sergilemektedir.

---

## 🚀 Kullanılan Teknolojiler

- Java 
- Spring Boot
- JPA (Hibernate)
- Maven
- Lombok
- JUnit (Testler için)

---

## 📁 Proje Yapısı

```
ileriSeviyeJavaProject
├── src
│   ├── main
│   │   ├── java/tr/edu/ogu/ceng/bill
│   │   │   ├── controller         # REST API'leri
│   │   │   ├── dto                # Veri Transfer Nesneleri
│   │   │   ├── entity             # Veritabanı varlıkları
│   │   │   ├── repository         # JPA Repository arayüzleri
│   │   │   ├── service            # İş mantığı servisleri
│   │   │   └── BillApplication    # Uygulama başlangıç noktası
│   │   └── resources
│   │       └── application.properties  # Temel yapılandırmalar
│   └── test
│       └── java/...              # Birim testleri ve repository testleri
```

---

## ⚙️ Kurulum ve Çalıştırma

### 1. Projeyi Klonlayın

```bash
git clone https://github.com/cevahirsahin/ileriSeviyeJavaProject.git
cd ileriSeviyeJavaProject
```

### 2. Maven ile Derleyin

```bash
./mvnw clean install
```

### 3. Uygulamayı Başlatın

```bash
./mvnw spring-boot:run
```

> Alternatif olarak IntelliJ IDEA ile açıp `BillApplication.java` dosyasından çalıştırabilirsiniz.

---

## 🧪 Test Çalıştırma

```bash
./mvnw test
```

---

## 👥 Geliştirici Ekibi

Bu proje, Eskişehir Osmangazi Üniversitesi öğrencileri tarafından grup çalışması olarak geliştirilmiştir.

---


