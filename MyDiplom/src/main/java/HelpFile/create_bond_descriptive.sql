drop TABLE  if exists BOND_DESCRIPTIVE ; 

CREATE TABLE BOND_DESCRIPTIVE(
  ID_BB_UNIQUE varchar(30), --not null
  ID_BB_COMPANY int NULL,
  ID_BB_SECURITY int NULL,
  TICKER varchar(50) NULL,
  MATURITY TIMESTAMP NULL,
  SERIES varchar(50) NULL,
  NAME varchar(255) NULL,
  SHORT_NAME varchar(50) NULL,
  ISSUER_INDUSTRY varchar(50) NULL,
  MARKET_SECTOR_DES varchar(50) NULL,
  CPN_FREQ int NULL,
  CPN_TYP varchar(50) NULL,
  MTY_TYP varchar(50) NULL,
  CALC_TYP_DES varchar(50) NULL,
  DAY_CNT int NULL,
  MARKET_ISSUE varchar(50) NULL,
  COUNTRY varchar(50) NULL,
  CRNCY varchar(50) NULL,
  COLLAT_TYP varchar(50) NULL,
  LEAD_MGR varchar(50) NULL,
  EXCH_CODE varchar(50) NULL,
  ANNOUNCE_DT timestamp  NULL,
  FIRST_SETTLE_DT timestamp  NULL,
  FIRST_CPN_DT timestamp  NULL,
  INT_ACC_DT timestamp  NULL,
  ISSUE_DT timestamp  NULL,
  ISSUE_PX decimal(18, 6) NULL,
  ID_EUROCLEAR varchar(50) NULL,
  ID_ISMA varchar(50) NULL,
  ID_SEDOL1 varchar(9) NULL,
  ID_SEDOL2 varchar(9) NULL,
  ID_CEDEL varchar(50) NULL,
  ID_WERTPAPIER varchar(50) NULL,
  ID_ISIN varchar(12) NULL,
  ID_DUTCH varchar(50) NULL,
  ID_VALOREN varchar(50) NULL,
  ID_FRENCH varchar(50) NULL,
  ID_COMMON varchar(50) NULL,
  ID_JAPAN varchar(50) NULL,
  ID_BELGIUM varchar(50) NULL,
  ID_DANISH varchar(50) NULL,
  ID_AUSTRIAN varchar(50) NULL,
  ID_LUXEMBOURG varchar(50) NULL,
  ID_SWEDISH varchar(50) NULL,
  ID_NORWAY varchar(50) NULL,
  ID_JAPAN_COMPANY varchar(50) NULL,
  ID_SPAIN varchar(50) NULL,
  ID_ITALY varchar(50) NULL,
  ID_CUSIP varchar(9) NULL,
  NXT_CALL_DT timestamp  NULL,
  NXT_CALL_PX decimal(18, 6) NULL,
  NXT_PAR_CALL_DT timestamp  NULL,
  NXT_PUT_DT timestamp  NULL,
  NXT_PUT_PX decimal(18, 6) NULL,
  NXT_PAR_PUT_DT timestamp  NULL,
  NXT_CPN_DT timestamp  NULL,
  NXT_SINK_DT timestamp  NULL,
  NXT_REFUND_DT timestamp  NULL,
  RTG_CANADIAN varchar(50) NULL,
  RTG_DOMINION varchar(50) NULL,
  RTG_MIKUNI varchar(50) NULL,
  RTG_JCR varchar(50) NULL,
  NXT_REFIX_DT timestamp  NULL,
  RESET_IDX varchar(50) NULL,
  PFD_DVD_PAY_DT timestamp  NULL,
  PFD_EX_DVD_DT timestamp  NULL,
  CV_COMMON_TICKER varchar(50) NULL,
  CV_COMMON_TICKER_EXCH varchar(50) NULL,
  CV_UNTIL timestamp  NULL,
  CV_PROV_PX decimal(16, 6) NULL,
  CALLABLE char(1) NULL,
  SINKABLE char(1) NULL,
  PUTABLE char(1) NULL,
  ID_BB_PARENT_CO int NULL,
  PARENT_COMP_NAME varchar(50) NULL,
  PARENT_COMP_TICKER varchar(50) NULL,
  CNTRY_OF_INCORPORATION varchar(50) NULL,
  INDUSTRY_SECTOR varchar(50) NULL,
  INDUSTRY_GROUP varchar(50) NULL,
  INDUSTRY_SUBGROUP varchar(50) NULL,
  COUNTRY_GUARANTOR varchar(50) NULL,
  CNTRY_OF_DOMICILE varchar(50) NULL,
  SECURITY_DES varchar(255) NULL,
  FLAG_144A char(1) NULL,
  SECURITY_TYP varchar(50) NULL,
  CV_START_DT timestamp  NULL,
  PRE_EURO_ID_ISIN varchar(50) NULL,
  PRE_EURO_CRNCY varchar(50) NULL,
  PRE_EURO_CALENDAR_CODE varchar(50) NULL,
  PRE_EURO_DAY_CNT int NULL,
  PRE_EURO_ISSUE_TYP varchar(50) NULL,
  PRE_EURO_CALC_TYP int NULL,
  POST_EURO_CRNCY varchar(50) NULL,
  POST_EURO_CALENDAR_CODE varchar(50) NULL,
  POST_EURO_ISSUE_TYP varchar(50) NULL,
  POST_EURO_DAY_CNT int NULL,
  POST_EURO_CALC_TYP int NULL,
  POST_EURO_ID_ISIN varchar(50) NULL,
  POST_EURO_ID_DUTCH varchar(50) NULL,
  POST_EURO_ID_COMMON varchar(50) NULL,
  POST_EURO_ID_FRENCH varchar(50) NULL,
  REDENOM_DT timestamp  NULL,
  REDENOM_METHOD varchar(50) NULL,
  REDENOM_ROUND_METHOD varchar(50) NULL,
  REDENOM_PX varchar(50) NULL,
  RECONVENTION_DT timestamp  NULL,
  GUARANTOR varchar(255) NULL,
  POST_EURO_CALC_TYP_DES varchar(50) NULL,
  POST_EURO_DAY_CNT_DES varchar(50) NULL,
  PRE_EURO_CALC_TYP_DES varchar(50) NULL,
  PRE_EURO_DAY_CNT_DES varchar(50) NULL,
  PRE_EURO_ID_COMMON varchar(50) NULL,
  PRE_EURO_ID_FRENCH varchar(50) NULL,
  PREV_CPN_DT timestamp  NULL,
  CALL_DISCRETE char(1) NULL,
  PUT_DISCRETE char(1) NULL,
  MAKE_WHOLE_CALL char(10) NULL,
  LONG_COMP_NAME varchar(8000) NULL,
  REDEMP_CRNCY varchar(50) NULL,
  CPN_CRNCY varchar(50) NULL,
  DTC_ELIGIBLE char(1) NULL,
  STRUCTURED_NOTE char(1) NULL,
  PCT_PAR_QUOTED char(1) NULL,
  PCS_QUOTE_TYP char(1) NULL,
  IS_UNIT_TRADED char(1) NULL,
  IS_REVERSE_CONVERTIBLE char(1) NULL,
  RTG_RI varchar(50) NULL,
  TRADE_CRNCY varchar(50) NULL,
  BEARER char(1) NULL,
  REGISTERED char(1) NULL,
  CALLED char(1) NULL,
  CALLED_DT timestamp  NULL,
  ISSUER varchar(255) NULL,
  CALL_FEATURE varchar(50) NULL,
  PUT_FEATURE varchar(50) NULL,
  PENULTIMATE_CPN_DT timestamp  NULL,
  FLT_CPN_CONVENTION varchar(50) NULL,
  FLOATER char(1) NULL,
  TRADE_STATUS char(1) NULL,
  CDR_COUNTRY_CODE varchar(50) NULL,
  CDR_SETTLE_CODE varchar(50) NULL,
  SEASONING_STATUS varchar(50) NULL,
  FINAL_MATURITY timestamp  NULL,
  PRVT_PLACE char(1) NULL,
  CALC_TYP int NULL,
  REMOVAL_REASON varchar(8000) NULL,
  IS_PERPETUAL char(1) NULL,
  IS_REG_S char(1) NULL,
  CALLED_PX decimal(12, 6) NULL,
  DEFAULTED char(1) NULL,
  GILTS_EX_DVD_DT timestamp  NULL,
  NXT_FACTOR_DT timestamp  NULL,
  OID_BOND char(1) NULL,
  DELIVERY_TYP varchar(50) NULL,
  ID_SEDOL3 varchar(50) NULL,
  ID_SEDOL4 varchar(50) NULL,
  ID_SEDOL5 varchar(50) NULL,
  SEDOL1_COUNTRY_ISO varchar(50) NULL,
  SEDOL2_COUNTRY_ISO varchar(50) NULL,
  SEDOL3_COUNTRY_ISO varchar(50) NULL,
  SEDOL4_COUNTRY_ISO varchar(50) NULL,
  SEDOL5_COUNTRY_ISO varchar(50) NULL,
  ID_MIC1 varchar(50) NULL,
  ID_MIC2 varchar(50) NULL,
  ID_MIC3 varchar(50) NULL,
  ID_MIC4 varchar(50) NULL,
  ID_MIC5 varchar(50) NULL,
  DUAL_CRNCY char(1) NULL,
  EXTENDIBLE char(1) NULL,
  EXCHANGEABLE char(1) NULL,
  IS_SOFT_CALL char(1) NULL,
  CV_MANDATORY_CNVS varchar(50) NULL,
  EU_DIRECTIVE varchar(50) NULL,
  PROCESSED bit NOT NULL DEFAULT 0::bit ,
  ID_CUSIP_REAL varchar(9) NULL,
  INDUSTRY_SUBGROUP_NUM int NULL,
  CV_UNDERLYING_ID_BB_UNIQUE varchar(30) NULL,
  EventID int NULL,
  RequestID int NULL,
  FLT_BENCH_MULTIPLIER decimal(24, 6) NULL,
  RTG_DBRS varchar(50) NULL,
  CALC_MATURITY timestamp  NULL,
  CALL_PARTIAL varchar(50) NULL,
  DTC_REGISTERED varchar(50) NULL,
  EST_CPN_FLAG varchar(50) NULL,
  FIRST_CALL_DT_ISSUANCE timestamp  NULL,
  ID_BB_GUARANTOR int NULL,
  IS_CURRENT_GOVT varchar(50) NULL,
  IS_DAY_PAYER char(1) NULL,
  LAST_REFIX_DT timestamp  NULL,
  SECURITY_TYP2 varchar(50) NULL,
  STEPUP_DT timestamp NULL,
  ISO_COUNTRY_GUARANTOR varchar(50) NULL,
  FLT_SPREAD decimal(12, 3) NULL,
  PUT_DAYS_NOTICE int NULL,
  YLD_YTM_MID decimal(28, 6) NULL,
  DUR_MID decimal(8, 4) NULL,
  DUR_ADJ_MID decimal(8, 4) NULL,
  YLD_CNV_MID decimal(28, 6) NULL,
  YLD_1D decimal(28, 6) NULL,
  YLD_YTC_MID decimal(28, 6) NULL,
  YLD_YTP_MID decimal(28, 6) NULL,
  ID_EXCH_SYMBOL varchar(50) NULL,
  CREDIT_ENHANCEMENTS varchar(50) NULL,
  INSURANCE_STATUS varchar(255) NULL,
  JUNIOR varchar(50) NULL,
  SENIOR varchar(50) NULL,
  FLT_PAY_DAY varchar(50) NULL,
  FLT_DAYS_PRIOR varchar(50) NULL,
  INFLATION_LINKED_INDICATOR varchar(50) NULL,
  DAYS_TO_SETTLE varchar(50) NULL,
  TYPE_OF_BOND varchar(50) NULL,
  CALL_NOTIFICATION_MIN_DAYS int NULL,
  UNDL_ID_BB_UNIQUE varchar(30) NULL,
  REFERENCE_INDEX varchar(50) NULL,
  BASE_CPI decimal(28, 10) NULL,
  CFI_CODE varchar(50) NULL,
  CPN decimal(28, 12) NULL,
  AMT_ISSUED decimal(28, 12) NULL,
  AMT_OUTSTANDING decimal(28, 12) NULL,
  MIN_PIECE decimal(28, 12) NULL,
  MIN_INCREMENT decimal(28, 12) NULL,
  PAR_AMT decimal(28, 12) NULL,
  REDEMP_VAL decimal(28, 12) NULL,
  REFIX_FREQ decimal(28, 12) NULL,
  IDX_RATIO decimal(28, 12) NULL,
  PFD_RST_DVD decimal(28, 12) NULL,
  CV_CNVS_RATIO decimal(28, 12) NULL,
  CV_CNVS_FEXCH_RT decimal(28, 12) NULL,
  BASIC_SPREAD decimal(28, 12) NULL,
  FLT_IDX_FACTOR decimal(28, 12) NULL,
  PRE_EURO_AMT_ISSUED decimal(28, 12) NULL,
  PRE_EURO_AMT_OUTSTANDING_LAST decimal(28, 12) NULL,
  PRE_EURO_AMT_OUTSTANDING decimal(28, 12) NULL,
  PRE_EURO_MIN_INCREMENT decimal(28, 12) NULL,
  PRE_EURO_MIN_PIECE decimal(28, 12) NULL,
  PRE_EURO_PAR_AMT decimal(28, 12) NULL,
  POST_EURO_AMT_ISSUED decimal(28, 12) NULL,
  POST_EURO_AMT_OUTSTANDING decimal(28, 12) NULL,
  POST_EURO_MIN_PIECE decimal(28, 12) NULL,
  POST_EURO_MIN_INCREMENT decimal(28, 12) NULL,
  POST_EURO_PAR_AMT decimal(28, 12) NULL,
  POST_EURO_AMT_OUTSTANDING_LAST decimal(28, 12) NULL,
  NXT_SINK_AMT decimal(28, 12) NULL,
  CUR_CPN decimal(28, 12) NULL,
  MOST_RECENT_REPORTED_FACTOR decimal(28, 12) NULL,
  CV_SH_PAR decimal(28, 12) NULL,
  CV_CNVS_PX decimal(28, 12) NULL,
  STEPUP_CPN decimal(28, 12) NULL,
  CPN_FREQ_YLD_CNV decimal(28, 10) NULL,
  DAY_PAYER_FREQ int NULL,
  EX_DIV_DAYS int NULL,
  EX_DIV_CALENDAR varchar(10) NULL,
  CONTINGENT_CONVERSION varchar(2) NULL,
  CONTRIB_DATA_INDICATOR varchar(50) NULL,
  WI_flag bit NULL,
  IsWI bit NOT NULL DEFAULT 0::bit,
  ID_BB_GLOBAL varchar(50) NULL,
  COMPOSITE_ID_BB_GLOBAL varchar(50) NULL,
  SECURITY_FACTORABLE bit NULL,
  CreateDate timestamp  NULL DEFAULT (NOW()),
  ChangeDate timestamp  NULL,
  OriginalDate timestamp  NULL,
  SINKING_FUND_FACTOR decimal(28, 12) NULL,
  SINK_SCHEDULE_AMT_TYP varchar(180) NULL,
  INDUSTRY_GROUP_NUM int NULL,
  INDUSTRY_SECTOR_NUM int NULL,
  ISSUERS_STOCK varchar(5) NULL,
  INFLATION_LAG int NULL,
  MAKE_WHOLE_CALL_SPREAD decimal(28, 12) NULL,
  ISSUER_BULK varchar(180) NULL,
  ID_BB_SEC_NUM_DES varchar(180) NULL,
  FEED_SOURCE varchar(180) NULL,
  ID_BB_GLOBAL_COMPANY varchar(180) NULL,
  ID_BB_GLOBAL_COMPANY_NAME varchar(180) NULL,
  UNDL_ID_BB_GLOBAL varchar(50) NULL,
  ID_CINS varchar (9) NULL,
  RowTimestamp timestamp NOT NULL DEFAULT (NOW()),
  GICS_SUB_INDUSTRY int NULL,
  GICS_SUB_INDUSTRY_NAME varchar (30) NULL
) ;

select * from BOND_DESCRIPTIVE;