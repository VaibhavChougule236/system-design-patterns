# 🚦 Building a Load Balancer from Scratch using Spring Boot  
*A System Design Learning Project*

When we start learning system design, most of the time we end up memorizing diagrams — load balancer in front, services behind, database somewhere at the bottom.  
But understanding **why these components exist and how they behave during failures** comes only when we actually build them.

This project is my attempt to **learn system design by implementing it**, starting with one of the most fundamental components of distributed systems — **a Load Balancer**.

Instead of directly using tools like AWS ELB, Nginx, or Spring Cloud LoadBalancer, I implemented a **custom load balancer from scratch using Java and Spring Boot**, focusing on internal logic, real-world behavior, and failure handling.

---

## 🎯 What This Project Demonstrates

This project explains and implements:

- How a load balancer distributes traffic
- How multiple instances of the same service are used for scaling
- How backend failures are detected automatically
- How traffic is rerouted without impacting users
- How real production systems achieve high availability

The goal is **learning and understanding**, not replacing production-grade tools.

---

## 🧠 Why Build a Load Balancer Manually?

In real systems:

- Servers crash
- Traffic spikes unexpectedly
- Instances are added and removed dynamically

A load balancer is responsible for:
- Routing traffic
- Monitoring health
- Preventing cascading failures

By building one ourselves, we learn:
- Why stateless services scale well
- What horizontal scaling actually means
- How failures are handled silently
- Why health checks are critical

This approach helps move from *theoretical system design* to *practical system thinking*.

---

## 🏗 High-Level Architecture


Client  	
|	  
| http://localhost:8080/hello	  
|	  
Load Balancer (Spring Boot)	    
|	  
|---- Backend Instance #1 (port 8081)	  
|	  
|---- Backend Instance #2 (port 8082)	  



Important detail:

All backend instances run **the same code**.  
They are differentiated only by **runtime configuration (port)**.

This closely mirrors how containers and pods work in real production systems.

---

## 📦 Project Structure

01-load-balancer/	    
│	  
├── backend-service/	  
│	   └── Stateless Spring Boot service (multiple instances)	  
│	  
├── load-balancer/	  
│	   └── Custom load balancer implementation	  
│	  
└── README.md		  




Each module has a **single responsibility**, which keeps the design clean and realistic.

---

## 🔹 Backend Service (Worker Nodes)

The backend service is intentionally simple:

- Exposes `/api/hello` to return a response
- Exposes `/health` to report its status
- Does **not know** that a load balancer exists

This reflects a real-world principle:

> Backend services should be stateless and unaware of traffic routing logic.

The same backend application is started multiple times using different ports:
- Instance 1 → `8081`
- Instance 2 → `8082`

This is horizontal scaling in practice.

---

## 🔹 Load Balancer (Decision Maker)

The load balancer:

- Accepts client requests
- Maintains a list of backend instances
- Selects a backend using a routing strategy
- Forwards the request and returns the response

Initially, **Round Robin** routing is implemented to distribute traffic evenly across instances.

---

## ⚙️ Load Balancing Strategy – Round Robin

Round Robin routing works like this:

- Request 1 → Backend on 8081
- Request 2 → Backend on 8082
- Request 3 → Backend on 8081
- Request 4 → Backend on 8082

This ensures:
- Fair traffic distribution
- No single backend is overloaded initially

The strategy uses a **thread-safe counter**, which is important when multiple requests arrive concurrently.

---

## ❤️ Health Check – Making It Real

A load balancer without health checks is incomplete.

To make the system realistic, we implement:

- Periodic health checks to `/health`
- Automatic marking of backend instances as healthy or unhealthy
- Routing traffic only to healthy instances

How it works:

- The load balancer runs a scheduled task
- Every few seconds, it pings each backend
- If a backend does not respond, it is marked unhealthy
- Unhealthy backends are removed from routing decisions

This is the same core idea used in:
- AWS Elastic Load Balancer

---

## 🔄 Failover Behavior

If the backend running on port `8081` goes down:

- Health check fails
- Load balancer marks it unhealthy
- All traffic is routed to `8082`
- Client does not see an error

From the user’s point of view, the system remains available.

This is the core goal of distributed systems — **graceful failure handling**.


