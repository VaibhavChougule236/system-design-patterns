# CDN Implementation using Spring Boot

## What is a CDN?

A **Content Delivery Network (CDN)** is used to serve **static content** (images, CSS, JavaScript, banners, public files) from servers located closer to users instead of serving everything from a single backend server.

The main idea is simple:
- Cache static content near users
- Serve repeat requests from cache
- Call the backend (origin) only when required

---

## Why CDN is Important in Real Industry Projects

In real applications like **e-commerce platforms**, most traffic is not for APIs but for **static assets** such as product images and banners.

Without a CDN:
- Backend servers get overloaded
- Page load time increases for global users
- Infrastructure cost becomes high

With a CDN:
- Static assets are served faster
- Backend servers are protected
- The system scales to millions of users

This is why companies like Amazon, Flipkart, Netflix, and Instagram use CDNs.

---

## How a CDN Works (Simple Flow)

1. User requests a static asset
2. CDN checks if the asset is already cached
3. If cache hit → serve immediately
4. If cache miss → fetch from origin server
5. Cache the response with a TTL
6. Serve the response to the user

---

## Project Overview

This repository implements a **CDN simulation** using **Java and Spring Boot** to understand how CDNs work internally.

It focuses on:
- Origin vs Edge separation
- Cache hit and cache miss
- TTL (Time-To-Live)
- Cache invalidation
- Reduced backend load

---

## Architecture

```text
Client (Browser)
   |
CDN Edge Server
   |
   |-- Cache (TTL-based)
   |
   |-- On Cache Miss
           |
           v
Origin Asset Server

