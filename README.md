# Student Management System | 学生管理系统
![Java CI](https://github.com/liver-m/StudentManagementSystem/actions/workflows/maven.yml/badge.svg)

## 📖 项目简介 (Introduction)
- **中文**：一个从传统的 JDBC/Servlet 架构起步，最终将进化为支持高并发、集成 AI 助手的生产级 Java 项目。
- **English**: A Java project evolving from legacy JDBC/Servlet to a high-concurrency, AI-integrated production platform.

## 🚀 进化路线图 (Roadmap)
- [x] **阶段 0 (Phase 0)**: 环境配置与自动化构建 (CI/CD)。
- [x] **阶段 1a**: Spring Boot 4 骨架 + Docker（已完成）。
- [x] **阶段 1b**: Java 21 虚拟线程（已完成）。
- [x] **阶段 2 (Phase 2)**: 云原生化docker-compose + MySQL(已完成)。
- [ ] **阶段 3 (Phase 3)**: AI 注入 (接入 LangChain4j 与 RAG 架构)。

## 🛠️ 技术栈 (Tech Stack)
- **后端 (Backend)**: Java 21 (虚拟线程), Spring Boot 4, JPA.
- **数据库 (Database)**: MySQL.
- **工具 (Tools)**: Docker, docker-compose, Maven, GitHub Actions.

## Docker运行
1. 复制配置文件: `cp docker-compose.example.yml docker-compose.yml`
2. 修改 `docker-compose.yml` 里的密码
3. 一键启动: `docker-compose up --build`