
# Apache Server Setup (Frontend + Reverse Proxy)

## Overview

In this project, Apache acts as:

- A **secure web server (HTTPS)** for the frontend (HTML, CSS, JS)
- A **reverse proxy** to the Spring Boot backend
- The entry point of the application architecture

```

Client (Browser)
│
▼
Apache Server (HTTPS - Port 443)
│
├── Serves Static Files (HTML, CSS, JS)
│
└── Reverse Proxy (/api → Spring Boot)
│
▼
Spring Boot Server (Port 5000)

```

---

# 1. Connect to EC2

```bash
ssh -i your-key.pem ec2-user@ec2-84287932443289734.compute.amazonaws.com
````

---

# 2. Install Apache

```bash
sudo yum update -y
sudo yum install httpd -y
```

---

# 3. Start Apache

```bash
sudo systemctl start httpd
sudo systemctl enable httpd
```

Verify:

```bash
sudo systemctl status httpd
```

---

# 4. Project Structure on Server

We will deploy the frontend inside Apache's root directory:

```bash
/var/www/html/
```

---

# 5. Upload Frontend Files

From your local machine:

```bash
scp -i your-key.pem -r apache-server/html/* ec2-user@ec2-84287932443289734.compute.amazonaws.com:/home/ec2-user/
```

Then move them:

```bash
sudo mv /home/ec2-user/* /var/www/html/
```

---

# 6. Configure Apache (VirtualHost)

Edit Apache config:

```bash
sudo nano /etc/httpd/conf/httpd.conf
```

Add at the end:

```apache
<VirtualHost *:80>
    ServerName ec2-84287932443289734.compute.amazonaws.com

    DocumentRoot /var/www/html

    <Directory "/var/www/html">
        AllowOverride All
        Require all granted
    </Directory>

    # Reverse Proxy to Spring Boot
    ProxyPreserveHost On
    ProxyPass /api http://localhost:5000/
    ProxyPassReverse /api http://localhost:5000/
</VirtualHost>
```

---

# 7. Enable Proxy Modules

```bash
sudo yum install mod_proxy mod_proxy_http -y
```

Restart Apache:

```bash
sudo systemctl restart httpd
```

---

# 8. Test HTTP Access

Open in browser:

```
http://ec2-84287932443289734.compute.amazonaws.com
```

You should see your frontend (`index.html`).

---

# 9. Test Backend Connection

Your frontend calls:

```js
fetch("/api/login")
```

Apache forwards it to:

```
http://localhost:5000/login
```

---

# 10. Enable HTTPS (TLS)

Now apply TLS:

```bash
sudo certbot --apache -d ec2-84287932443289734.compute.amazonaws.com
```

---

# 11. Force HTTPS Redirect

Certbot usually configures this automatically, but verify:

```apache
<VirtualHost *:80>
    Redirect permanent / https://ec2-84287932443289734.compute.amazonaws.com/
</VirtualHost>
```

---

# 12. Open Security Group Ports

In AWS EC2:

| Port | Purpose |
| ---- | ------- |
| 80   | HTTP    |
| 443  | HTTPS   |
| 22   | SSH     |

---

# 13. Final Test

Visit:

```
https://ec2-84287932443289734.compute.amazonaws.com
```

 Frontend loads securely
 HTTPS enabled
API calls work via `/api`

---

# Key Concepts Applied

* Reverse Proxy Architecture
* Separation of concerns (Frontend vs Backend)
* Secure communication using HTTPS
* Apache as API Gateway
* Integration with Spring Boot

---

# Next Steps

* Add authentication tokens (JWT)
* Enable CORS policies
* Harden Apache security headers
* Use Load Balancer (AWS ALB)

---

# Author

Ricardo Ayala
Secure Application Design Lab
