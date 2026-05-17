# Project Overview

## 1. Introduction

MCQ Platform API is a Spring Boot REST API designed to help users
practice and evaluate their knowledge through MCQ-based learning.

The API currently supports two major learning systems:

1. Random Practice Questions
2. Timed Practice Sets (Quiz Mode)

Users can practice topic-specific questions, retrieve answers,
attempt timed tests, submit responses, and view results after
completion.

---

## 2. Purpose of the Project

The purpose of this project is to provide a structured backend
system for skill practice and assessment through MCQ-based
learning.

The API allows users to:

- Practice random questions by filters
- Test knowledge through timed practice sets
- Submit answers
- View performance and results

---

## 3. Problem Statement

Traditional practice systems often lack flexibility in question
selection and structured performance evaluation.

This project solves these issues by providing:

- Topic-based random question generation
- Subject filtering
- Custom question size selection
- Timed assessments
- Result tracking using practice-set IDs

---

## 4. Core Features

### 4.1 Practice Question System

Users can retrieve random questions for practice using filters.

Supported filters include:

- Topic
- Subject
- Number of questions (size)

Each question contains a unique ID that can later be used to
retrieve the correct answer through a dedicated endpoint.

Features:

- Randomized question generation
- Topic filtering
- Subject filtering
- Configurable question count
- Answer retrieval by question ID

Example workflow:

User selects:
Subject → Topic → Question Count
        ↓
API returns random questions
        ↓
User solves questions
        ↓
Answers fetched using Question ID

---

### 4.2 Practice Set / Quiz System

Users can test their skills using structured practice sets.

Each practice set contains:

- Fixed questions
- Time management support
- Answer submission
- Result calculation

After completion, users can retrieve results using the
Practice Set ID.

Features:

- Timed assessment
- Practice set management
- Answer submission
- Automatic score calculation
- Result retrieval

Example workflow:

Start Practice Set
        ↓
Timer Starts
        ↓
User submits answers
        ↓
Result generated
        ↓
Result fetched using Practice Set ID

---

## 5. High-Level Workflow

Practice Question Flow:

User Request
      ↓
Select Subject + Topic + Size
      ↓
Random Questions Returned
      ↓
User Solves Questions
      ↓
Answer Retrieved via Question ID

Practice Set Flow:

Start Practice Set
      ↓
Timer Starts
      ↓
Attempt Questions
      ↓
Submit Answers within time
      ↓
Result Generated 
      ↓
View Result by Practice Set ID

---

## 6. System Modules

### Question Module
Handles random question generation using subject, topic,
and size filters.

### Answer Module
Provides correct answers using question IDs.

### Practice Set Module
Manages quiz sessions and timed practice sets.

### Submission Module
Handles user answer submission.

### Result Module
Calculates and retrieves user results.

---

## 7. Technology Summary

| Technology | Purpose |
|------------|----------|
| Spring Boot | Backend Framework |
| Spring Web | REST APIs | 
| Spring Data JPA | ORM |
| MySQL | Database |
| Maven | Dependency Management |
| JWT | Authentication |


---

## 8. API Base Information

Base URL:

http://localhost:8080/

Response Format:

JSON

---
