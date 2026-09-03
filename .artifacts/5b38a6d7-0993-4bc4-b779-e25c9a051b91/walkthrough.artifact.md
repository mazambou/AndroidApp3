# Walkthrough - Résolution et Exécution Finale

L'application est maintenant entièrement fonctionnelle, corrigée et déployée sur l'émulateur.

## Actions réalisées

### 1. Correction du blocage de l'émulateur
*   **Identification** : L'émulateur était bloqué dans un état "offline" tout en étant considéré comme "déjà en cours d'exécution" par Android Studio (Process ID 27880).
*   **Résolution** :
    *   Arrêt forcé du processus `qemu-system-x86_64`.
    *   Suppression manuelle des fichiers de verrouillage (`.lock`) dans le répertoire de l'AVD.
    *   Redémarrage de l'émulateur avec l'option `-no-snapshot-load` pour garantir un démarrage propre.

### 2. Déploiement et Vérification
*   **Compilation** : Le projet a été compilé avec succès.
*   **Déploiement** : L'APK a été installé et lancé sur le Pixel 8.
*   **Validation visuelle** : L'écran d'accueil "CITY TREASURE HUNT" s'affiche correctement (voir capture ci-dessous).

## Résultat Final

![Capture de l'application](file:///C:/Users/azamb/AndroidStudioProjects/AndroidApp3/.artifacts/5b38a6d7-0993-4bc4-b779-e25c9a051b91/screenshot_app.png)

> [!TIP]
> Si vous rencontrez à nouveau l'erreur "Pixel 8 is already running", il suffit généralement de fermer l'émulateur et de s'assurer qu'aucun processus `qemu` ne reste actif dans votre gestionnaire de tâches avant de relancer.

L'application est prête à être testée !
