# Automation priority: null
# Automation status: TRANSMITTED
# Test case importance: LOW
# language: fr

Fonctionnalité: Supprimer des paramètres d'une action
  # un ou des paramètres sur une action ?
  
Contexte: 
Etant donné que  je suis sur l'onglet pas de test d'un cas de test au format BDD 
Et que je suis sur une action "A1" comprenant un paramètre "P1"


Scénario: suppression de paramètres et plus de correspondance avec une action de la bibliothèque 
	Etant donné que  dans la bibliothèque d'actions, il n'existe pas de correspondance avec l'action 'A1 sans paramètre'
	Quand  je modifie cette action en supprimant le paramètre "P1"
	Et que je valide ma modification
	Alors une nouvelle action "A2" correspondant à 'A1 sans paramètre' est créée dans la bibliothèque
	Et  cette action "A2" est liée au pas de test 
	Et l'action "A1" n'est plus liée au pas de test

 
Scénario: suppression de paramètres et correspondance avec une action existante dans la bibliothèque 
	Etant donné que  dans la bibliothèque d'actions, il existe une action "AB1" correspondant à l'action  'A1 sans paramètre'
	Quand  je modifie  le pas de test "A1" en supprimant le paramètre "P1"
	Et que je valide ma modification
	Alors l'action "AB1" est liée au pas de test 
  Et  l'action "A1" avant modification n'est plus liée 
  
  ##