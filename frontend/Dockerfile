# Utiliser une image de base Node.js
FROM node:alpine3.20

# Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Copier le fichier package.json et package-lock.json
COPY package*.json ./

# Installer les dépendances
RUN npm install

# Copier le reste des fichiers de l'application
COPY . .

# Construire l'application React
RUN npm run build

# Utiliser une image de base Node.js pour servir l'application
FROM node:alpine3.20

WORKDIR /app

# Ne copier que les fichiers nécessaires pour servir l'application
COPY --from=0 /app/dist /app/dist

# Installer serve pour servir l'application
RUN npm install -g serve

# Exposer le port 3000
EXPOSE 3000

# Démarrer l'application
CMD [ "serve", "-s", "dist" ]
