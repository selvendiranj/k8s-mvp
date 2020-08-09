### STAGE 1: Build ###
FROM node:alpine AS builder

WORKDIR /app

COPY . .

RUN npm install && \
    npm run build

### STAGE 2: Run ###
FROM nginx:alpine

COPY --from=builder /app/dist/* /usr/share/nginx/html/
