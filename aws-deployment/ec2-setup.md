# EC2 Setup - Secure Application Design

Provision an AWS EC2 instance to deploy the secure application architecture.

---

## Create EC2 Instance

1. Go to AWS Console → EC2
2. Click **Launch Instance**
3. Configure:

- AMI: Amazon Linux 2023
- Instance type: t2.micro (Free Tier)
- Key pair: Create or select existing
- Network settings:
    - Allow:
        - SSH (22)
        - HTTP (80)
        - HTTPS (443)
        - Custom TCP (8080) ← Spring Boot

---

## Connect to EC2

```bash
ssh -i "ec2-84287932443289734.compute.amazonaws.com" ec2-user@<EC2--84287932443289734>