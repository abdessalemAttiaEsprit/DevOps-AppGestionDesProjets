# ==========================================
# Étape 1 : Build Angular
# ==========================================
FROM node:24-alpine AS build

WORKDIR /app

# Copier les fichiers de dépendances
COPY package*.json ./

# Installer les dépendances
RUN npm install

# Copier le projet Angular
COPY . .

# Compiler l'application Angular
RUN npm run build


# ==========================================
# Étape 2 : Serveur Nginx
# ==========================================
FROM nginx:alpine

# Supprimer la configuration Nginx par défaut
RUN rm -rf /usr/share/nginx/html/*

# Copier le build Angular
COPY --from=build /app/dist/frontend /usr/share/nginx/html

# Exposer le port HTTP
EXPOSE 80

# Démarrer Nginx
CMD ["nginx", "-g", "daemon off;"]
