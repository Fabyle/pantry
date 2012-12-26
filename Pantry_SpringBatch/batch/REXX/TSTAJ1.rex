/*########################################################*/
/* NOM      : Descripton					              */
/* AUTEUR   : L.LACROIX									  */
/* DATE     : 17/11/2011                                  */
/* FONCTION :                                             */
/*--------------------------------------------------------*/
/* REVISION 1                                             */
/* AUTEUR   :                                             */
/* DATE     :                                             */
/* OBJET    :                                             */
/*--------------------------------------------------------*/
/* VERSION.REVISION : 1.0                                 */
/*########################################################*/
/* TRACE ALL */

S010:
/* ====================================================== */
/* = S010 : Initialisations                             = */
/* ====================================================== */

/* Initialisation des variables globales */
   ENV = 'TI'
   IDDOMAINE = 'WEB'
   BATCHID = 'TSTAJ1'
   BATCHCLASSPATH = 'com/systalians/qlf/saf/proto/springbatch/jobs/job-context.xml'
   JOB = BATCHID'-ExempleSpringBatch'
   MAIN = 'org.springframework.batch.core.launch.support.CommandLineJobRunner'
   JAVA = 'java'

   REPLOG = 'C:\applications\Batch\LOG'
   REPREX = 'C:\applications\Batch\'ENV'\'IDDOMAINE'\REXX'
   REPPARM = 'C:/applications/Batch/'ENV'/'IDDOMAINE'/PARM'
   REPJAVA = 'C:/applications/Batch/'ENV'/'IDDOMAINE'/JAVA/'BATCHID
   REPTEMP = 'C:\applications\Batch\'ENV'\'IDDOMAINE'\TEMP'

   REPMODULES = REPREX'\MODULES'
   CALL VALUE('REGINA_MACROS', REPMODULES , 'SYSTEM')
   
   DATRAIT = DATE(S)'_'RIGHT(TIME(M), 4, '0')
   RETOUR = 0
   RCSTEP = 0
   RCSQL = 0
   ENR = ''
   LIG = ''
   MSDOS = ''

S010FIN:

S020:
/* ====================================================== */
/* = S020 : Definition des fichiers                     = */
/* ====================================================== */

/* Fichier log */
   FICLOG = REPLOG'\'JOB'_'DATRAIT'.log'
   LIG = 'Debut du traitement 'JOB
   CALL ECRIT_LOG(FICLOG, 'I', LIG)
   
S020FIN:

S030:
/* ====================================================== */
/* = S030 : Traitement des arguments REXX               = */
/* ====================================================== */
   LIG = 'Step 30 : aucuns arguments REXX '
   CALL ECRIT_LOG(FICLOG, 'I', LIG)
   
S030FIN:

S040:
/* ====================================================== */
/* = S040 : traitement                                  = */
/* ====================================================== */
   LIG = 'Step 40 : Lancement 'JOB
   CALL ECRIT_LOG(FICLOG, 'I', LIG)
   
   /* Lancement du Batch Java */
   EXEC_JAVA = JAVA' -cp "'REPJAVA'/*" -Dbatch.properties.file='REPPARM'/'BATCHID'_batch.properties -Dbatch.launch.datetime='DATRAIT' -Dlogback.configurationFile="'REPPARM'/'BATCHID'_logback.xml" 'MAIN' classpath:'BATCHCLASSPATH' 'JOB' -next'
   CALL ECRIT_LOG(FICLOG, 'I', 'EXEC_JAVA='EXEC_JAVA)
   say (EXEC_JAVA)
   EXEC_JAVA
   
   RCSTEP = RC
   IF RCSTEP <> 0 THEN DO
      LIG = '  - ERREUR sur commande : 'EXEC_JAVA
      CALL ECRIT_LOG(FICLOG, 'E', LIG)
      RETOUR = 1
      SIGNAL S999
   END
   ELSE DO
      LIG = '  - Execution OK sur commande : 'EXEC_JAVA
      CALL ECRIT_LOG(FICLOG, 'I', LIG)
   END

S040FIN:

S999:
/* ====================================================== */
/* = S999 : Fin du traitement                           = */
/* ====================================================== */

   LIG = 'Fin du traitement 'JOB' - Code retour : 'RETOUR
   CALL ECRIT_LOG(FICLOG, 'I', LIG)
   /*
   IF RETOUR <> 0 THEN DO
      CAWTO '.BATCH_E1_000 : 'JOB' : FIN EN ERREUR : Code retour : 'RETOUR
   END
   ELSE DO
      CAWTO '.BATCH_I0_000 : 'JOB' : FIN NORMALE'
   END
   */
   EXIT (RETOUR)
