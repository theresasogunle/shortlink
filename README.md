# 🔗 ShortLink - URL Shortener Service

A full-stack URL shortening service built with Java Spring Boot. This project demonstrates backend development skills including REST API design, database management, and cloud
deployment.

## 🚀 Live Demo

**API Base URL:** `https://shortlink-nmca.onrender.com`

⚠️ **Note:** Hosted on Render's free tier - first request may take ~30 seconds (cold start).

## 📋 Features

- ✂️ Shorten long URLs to 6-character codes
- 🔄 Automatic redirect from short links to original URLs
- 📊 Click tracking and analytics
- ✅ URL validation
- 🗄️ PostgreSQL database for persistent storage

## 🛠️ Tech Stack

**Backend:**
- Java 21
- Spring Boot 3.4.x
- Spring Data JPA
- PostgreSQL
- Maven
- Lombok

**Deployment:**
- Docker
- Render (PostgreSQL + Web Service)

## 📡 API Endpoints

### Shorten a URL
  ```bash
  POST /api/shorten
  Content-Type: application/json

  {
    "url": "https://example.com"
  }

  Response:
  {
    "id": 1,
    "originalUrl": "https://example.com",
    "shortCode": "aB3xY9",
    "shortUrl": "https://your-app.onrender.com/aB3xY9",
    "createdAt": "2025-11-27T10:30:00",
    "clickCount": 0
  }

  Redirect to Original URL

  GET /{shortCode}
  Redirects to the original URL and increments click count.

  Get URL Statistics

  GET /api/stats/{shortCode}

  Returns analytics including click count and creation date.

  🏃 Running Locally

  Prerequisites

  - Java 21
  - Maven

  📝 License

  MIT License - feel free to use this project for learning!