# TLS Setup - Let's Encrypt (HTTPS Configuration)

## Objective

Secure the application using **TLS (Transport Layer Security)** to ensure encrypted communication between the client (browser) and the server (Apache).

---

## Application URL

```bash
https://ec2-84287932443289734.compute.amazonaws.com/
```

---

##  What is TLS?

TLS (Transport Layer Security) is a protocol that provides:

* Data encryption 
* Integrity 
* Secure communication between client and server

Without TLS:

```text
http:// → Insecure
```

With TLS:

```text
https:// → Secure
```

---

## Install Certbot (Let's Encrypt client)

```bash
sudo yum install certbot python3-certbot-apache -y
```

---

## Generate SSL Certificate

Run:

```bash
sudo certbot --apache
```

Follow the steps:

* Enter your email
* Accept terms of service
* Choose your domain:

```text
ec2-84287932443289734.compute.amazonaws.com
```

---

## Automatic Apache Configuration

Certbot will automatically:

Generate SSL certificates
Configure Apache
Enable HTTPS
Configure secure protocols

---

## Verify HTTPS

Open in browser:

```text
https://ec2-84287932443289734.compute.amazonaws.com/
```

You should see:

Secure connection enabled

---

## Force HTTPS Redirection

If not configured automatically, edit Apache config:

```bash
sudo nano /etc/httpd/conf.d/secureapp.conf
```

Add:

```apache
<VirtualHost *:80>
    ServerName ec2-84287932443289734.compute.amazonaws.com
    Redirect "/" "https://ec2-84287932443289734.compute.amazonaws.com/"
</VirtualHost>
```

Restart Apache:

```bash
sudo systemctl restart httpd
```

---

## Certificate Files Location

Certificates are stored in:

```bash
/etc/letsencrypt/live/ec2-84287932443289734.compute.amazonaws.com/
```

Files:

* fullchain.pem
* privkey.pem

---

## Auto-Renew Certificates

Test renewal:

```bash
sudo certbot renew --dry-run
```

Certificates renew automatically every 90 days.

---

##  Security Benefits

Encrypted communication
Secure login credentials
Protection against MITM attacks
Compliance with secure application design

---

##  Architecture with TLS

```text
Browser (HTTPS)
      │
      ▼
Apache Server (TLS + Frontend)
      │
      ▼
Spring Boot (Backend API)
```

---

## Final Result

Application available securely at:

```text
https://ec2-84287932443289734.compute.amazonaws.com/
```

---

## Conclusion

TLS was successfully implemented using Let's Encrypt, ensuring:

* Secure data transmission
* Encrypted user authentication
* Production-ready web security configuration
