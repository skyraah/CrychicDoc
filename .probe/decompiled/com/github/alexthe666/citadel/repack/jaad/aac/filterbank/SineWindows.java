package com.github.alexthe666.citadel.repack.jaad.aac.filterbank;

interface SineWindows {

    float[] SINE_1024 = new float[] { 7.669903E-4F, 0.002300969F, 0.0038349426F, 0.005368907F, 0.0069028586F, 0.008436794F, 0.00997071F, 0.011504602F, 0.013038468F, 0.014572302F, 0.016106103F, 0.017639864F, 0.019173585F, 0.02070726F, 0.022240888F, 0.023774462F, 0.025307981F, 0.02684144F, 0.028374836F, 0.029908165F, 0.031441424F, 0.03297461F, 0.034507714F, 0.036040742F, 0.037573684F, 0.039106537F, 0.040639296F, 0.042171963F, 0.04370453F, 0.04523699F, 0.046769347F, 0.048301592F, 0.049833726F, 0.05136574F, 0.052897636F, 0.05442941F, 0.05596105F, 0.057492558F, 0.059023935F, 0.06055517F, 0.062086266F, 0.063617215F, 0.06514801F, 0.06667866F, 0.06820914F, 0.06973947F, 0.07126963F, 0.07279963F, 0.07432945F, 0.07585911F, 0.07738858F, 0.07891786F, 0.080446966F, 0.08197588F, 0.0835046F, 0.085033126F, 0.08656145F, 0.08808957F, 0.08961748F, 0.09114519F, 0.092672676F, 0.09419994F, 0.09572699F, 0.097253814F, 0.09878041F, 0.10030677F, 0.1018329F, 0.10335878F, 0.10488442F, 0.10640982F, 0.10793497F, 0.109459855F, 0.11098449F, 0.11250886F, 0.11403298F, 0.115556814F, 0.11708038F, 0.11860368F, 0.12012669F, 0.121649414F, 0.12317186F, 0.12469402F, 0.12621588F, 0.12773745F, 0.1292587F, 0.13077967F, 0.13230032F, 0.13382065F, 0.13534068F, 0.13686039F, 0.13837977F, 0.13989884F, 0.14141756F, 0.14293596F, 0.14445402F, 0.14597175F, 0.14748912F, 0.14900614F, 0.15052283F, 0.15203916F, 0.15355512F, 0.15507074F, 0.15658598F, 0.15810084F, 0.15961535F, 0.16112947F, 0.16264322F, 0.16415659F, 0.16566956F, 0.16718215F, 0.16869435F, 0.17020614F, 0.17171754F, 0.17322853F, 0.17473911F, 0.1762493F, 0.17775905F, 0.17926839F, 0.18077731F, 0.1822858F, 0.18379387F, 0.1853015F, 0.18680869F, 0.18831545F, 0.18982176F, 0.19132763F, 0.19283305F, 0.19433801F, 0.19584252F, 0.19734657F, 0.19885014F, 0.20035325F, 0.2018559F, 0.20335807F, 0.20485975F, 0.20636095F, 0.20786168F, 0.20936191F, 0.21086164F, 0.21236089F, 0.21385963F, 0.21535787F, 0.2168556F, 0.21835282F, 0.21984953F, 0.22134572F, 0.2228414F, 0.22433653F, 0.22583115F, 0.22732525F, 0.22881879F, 0.23031181F, 0.23180428F, 0.2332962F, 0.23478758F, 0.2362784F, 0.23776866F, 0.23925838F, 0.24074753F, 0.24223611F, 0.24372411F, 0.24521154F, 0.24669841F, 0.24818468F, 0.24967039F, 0.2511555F, 0.25264F, 0.25412393F, 0.25560725F, 0.25708997F, 0.25857207F, 0.2600536F, 0.26153448F, 0.26301476F, 0.26449442F, 0.26597348F, 0.26745188F, 0.26892966F, 0.2704068F, 0.27188334F, 0.2733592F, 0.27483445F, 0.27630904F, 0.27778298F, 0.27925625F, 0.28072888F, 0.28220084F, 0.28367212F, 0.28514278F, 0.28661272F, 0.28808203F, 0.28955063F, 0.29101855F, 0.2924858F, 0.29395235F, 0.2954182F, 0.29688337F, 0.29834786F, 0.29981163F, 0.3012747F, 0.30273703F, 0.30419868F, 0.3056596F, 0.30711982F, 0.3085793F, 0.31003806F, 0.31149608F, 0.31295338F, 0.31440994F, 0.31586576F, 0.31732082F, 0.31877515F, 0.32022873F, 0.32168156F, 0.32313362F, 0.32458493F, 0.32603547F, 0.32748523F, 0.32893425F, 0.3303825F, 0.33182994F, 0.3332766F, 0.3347225F, 0.3361676F, 0.3376119F, 0.33905542F, 0.34049815F, 0.34194008F, 0.34338117F, 0.34482148F, 0.34626096F, 0.34769964F, 0.3491375F, 0.35057455F, 0.35201076F, 0.35344616F, 0.3548807F, 0.35631442F, 0.3577473F, 0.35917935F, 0.3606105F, 0.36204088F, 0.36347038F, 0.364899F, 0.36632678F, 0.36775368F, 0.36917976F, 0.37060493F, 0.37202924F, 0.37345266F, 0.37487522F, 0.3762969F, 0.3777177F, 0.3791376F, 0.3805566F, 0.38197473F, 0.38339192F, 0.38480824F, 0.38622364F, 0.38763815F, 0.38905174F, 0.3904644F, 0.39187613F, 0.39328697F, 0.39469686F, 0.39610586F, 0.3975139F, 0.398921F, 0.40032718F, 0.4017324F, 0.40313667F, 0.40454F, 0.40594238F, 0.4073438F, 0.40874428F, 0.4101438F, 0.41154233F, 0.41293988F, 0.4143365F, 0.41573212F, 0.41712677F, 0.41852042F, 0.4199131F, 0.4213048F, 0.4226955F, 0.4240852F, 0.4254739F, 0.4268616F, 0.42824832F, 0.429634F, 0.4310187F, 0.43240237F, 0.43378502F, 0.43516666F, 0.43654725F, 0.43792683F, 0.4393054F, 0.4406829F, 0.44205937F, 0.4434348F, 0.4448092F, 0.44618255F, 0.44755486F, 0.4489261F, 0.45029628F, 0.45166543F, 0.45303348F, 0.45440048F, 0.4557664F, 0.45713127F, 0.45849505F, 0.45985776F, 0.4612194F, 0.46257994F, 0.46393937F, 0.46529773F, 0.466655F, 0.46801114F, 0.46936622F, 0.47072017F, 0.47207302F, 0.47342476F, 0.47477537F, 0.47612488F, 0.4774733F, 0.47882056F, 0.48016667F, 0.48151168F, 0.48285556F, 0.4841983F, 0.4855399F, 0.48688036F, 0.48821968F, 0.48955783F, 0.49089485F, 0.4922307F, 0.4935654F, 0.49489895F, 0.4962313F, 0.4975625F, 0.49889255F, 0.5002214F, 0.50154907F, 0.50287557F, 0.5042009F, 0.50552505F, 0.506848F, 0.5081697F, 0.50949025F, 0.5108096F, 0.51212776F, 0.5134447F, 0.51476043F, 0.516075F, 0.5173883F, 0.5187004F, 0.52001125F, 0.52132094F, 0.5226294F, 0.52393657F, 0.5252425F, 0.52654725F, 0.52785075F, 0.529153F, 0.530454F, 0.5317537F, 0.5330522F, 0.53434944F, 0.5356455F, 0.53694016F, 0.53823364F, 0.53952587F, 0.5408168F, 0.54210645F, 0.5433948F, 0.5446819F, 0.54596776F, 0.5472523F, 0.5485355F, 0.5498175F, 0.55109817F, 0.5523775F, 0.55365556F, 0.55493236F, 0.5562078F, 0.55748194F, 0.5587548F, 0.5600263F, 0.5612965F, 0.5625654F, 0.56383294F, 0.5650992F, 0.5663641F, 0.56762767F, 0.5688899F, 0.5701508F, 0.57141036F, 0.57266855F, 0.57392544F, 0.57518095F, 0.5764351F, 0.5776879F, 0.5789393F, 0.5801894F, 0.5814381F, 0.5826855F, 0.58393145F, 0.58517605F, 0.5864193F, 0.58766115F, 0.5889016F, 0.5901407F, 0.5913784F, 0.59261465F, 0.5938496F, 0.59508306F, 0.5963152F, 0.59754586F, 0.5987752F, 0.60000306F, 0.60122955F, 0.6024546F, 0.6036782F, 0.6049005F, 0.60612124F, 0.60734063F, 0.6085586F, 0.60977507F, 0.61099017F, 0.6122038F, 0.613416F, 0.61462677F, 0.6158361F, 0.6170439F, 0.6182503F, 0.6194553F, 0.62065876F, 0.6218608F, 0.62306136F, 0.6242605F, 0.6254581F, 0.62665427F, 0.627849F, 0.6290422F, 0.63023394F, 0.6314242F, 0.63261294F, 0.6338002F, 0.634986F, 0.63617027F, 0.63735306F, 0.63853437F, 0.6397142F, 0.64089245F, 0.6420692F, 0.6432445F, 0.64441824F, 0.6455905F, 0.6467612F, 0.6479304F, 0.64909804F, 0.6502642F, 0.6514288F, 0.6525919F, 0.6537534F, 0.6549134F, 0.6560719F, 0.6572288F, 0.6583842F, 0.65953803F, 0.6606903F, 0.661841F, 0.66299015F, 0.6641378F, 0.6652838F, 0.66642827F, 0.6675712F, 0.6687125F, 0.66985226F, 0.67099047F, 0.67212707F, 0.67326206F, 0.6743955F, 0.6755274F, 0.6766576F, 0.6777863F, 0.67891335F, 0.68003887F, 0.6811627F, 0.682285F, 0.6834057F, 0.6845247F, 0.6856422F, 0.68675804F, 0.68787223F, 0.6889849F, 0.69009584F, 0.6912052F, 0.6923129F, 0.69341904F, 0.6945235F, 0.6956263F, 0.6967275F, 0.6978271F, 0.698925F, 0.70002127F, 0.7011159F, 0.7022089F, 0.7033002F, 0.70438987F, 0.7054779F, 0.70656425F, 0.70764893F, 0.70873195F, 0.7098133F, 0.710893F, 0.711971F, 0.7130473F, 0.714122F, 0.71519494F, 0.7162663F, 0.7173359F, 0.7184038F, 0.71947F, 0.72053456F, 0.72159743F, 0.7226586F, 0.723718F, 0.72477573F, 0.7258318F, 0.7268861F, 0.7279387F, 0.7289896F, 0.7300388F, 0.7310863F, 0.732132F, 0.73317605F, 0.73421836F, 0.73525894F, 0.7362978F, 0.7373349F, 0.7383703F, 0.7394039F, 0.74043584F, 0.741466F, 0.7424944F, 0.7435211F, 0.744546F, 0.74556917F, 0.74659055F, 0.7476102F, 0.7486281F, 0.7496442F, 0.75065863F, 0.7516712F, 0.75268203F, 0.75369114F, 0.7546984F, 0.7557039F, 0.75670767F, 0.7577096F, 0.7587098F, 0.75970817F, 0.76070476F, 0.76169956F, 0.7626926F, 0.7636838F, 0.76467323F, 0.7656609F, 0.7666467F, 0.7676307F, 0.7686129F, 0.7695933F, 0.7705719F, 0.7715487F, 0.77252364F, 0.7734968F, 0.7744681F, 0.77543765F, 0.77640533F, 0.77737117F, 0.7783352F, 0.77929735F, 0.78025776F, 0.78121626F, 0.7821729F, 0.7831278F, 0.7840808F, 0.7850319F, 0.78598124F, 0.7869287F, 0.78787434F, 0.78881806F, 0.78976F, 0.7907F, 0.7916382F, 0.7925745F, 0.79350895F, 0.7944415F, 0.79537225F, 0.79630107F, 0.79722804F, 0.79815316F, 0.7990764F, 0.7999977F, 0.80091715F, 0.8018347F, 0.8027504F, 0.8036642F, 0.8045761F, 0.8054861F, 0.8063942F, 0.80730045F, 0.8082047F, 0.8091071F, 0.81000763F, 0.81090623F, 0.811803F, 0.81269777F, 0.8135906F, 0.81448156F, 0.8153706F, 0.8162577F, 0.8171429F, 0.8180262F, 0.81890756F, 0.81978697F, 0.82066447F, 0.82154006F, 0.8224137F, 0.8232854F, 0.82415515F, 0.825023F, 0.8258889F, 0.8267528F, 0.8276148F, 0.8284748F, 0.82933295F, 0.83018905F, 0.83104324F, 0.8318955F, 0.8327458F, 0.8335941F, 0.8344404F, 0.8352848F, 0.8361273F, 0.8369677F, 0.8378062F, 0.8386427F, 0.83947724F, 0.84030986F, 0.84114045F, 0.841969F, 0.84279567F, 0.8436203F, 0.84444296F, 0.84526366F, 0.8460823F, 0.84689903F, 0.84771377F, 0.8485265F, 0.84933716F, 0.8501459F, 0.85095257F, 0.8517573F, 0.85256F, 0.8533607F, 0.8541594F, 0.8549561F, 0.85575074F, 0.8565434F, 0.857334F, 0.85812265F, 0.85890925F, 0.8596939F, 0.86047643F, 0.86125696F, 0.86203545F, 0.8628119F, 0.86358637F, 0.8643588F, 0.8651292F, 0.86589754F, 0.8666639F, 0.8674281F, 0.86819035F, 0.86895055F, 0.86970866F, 0.8704648F, 0.87121886F, 0.87197083F, 0.8727208F, 0.8734687F, 0.87421453F, 0.8749583F, 0.8757F, 0.8764397F, 0.87717724F, 0.8779128F, 0.87864625F, 0.87937766F, 0.880107F, 0.8808343F, 0.88155943F, 0.88228256F, 0.8830036F, 0.88372254F, 0.88443947F, 0.88515425F, 0.88586694F, 0.8865776F, 0.8872861F, 0.88799256F, 0.88869697F, 0.88939923F, 0.8900994F, 0.8907975F, 0.8914935F, 0.8921874F, 0.8928792F, 0.8935689F, 0.8942565F, 0.894942F, 0.89562535F, 0.89630663F, 0.89698577F, 0.8976628F, 0.8983378F, 0.8990106F, 0.8996813F, 0.9003499F, 0.9010164F, 0.90168077F, 0.902343F, 0.9030031F, 0.9036611F, 0.90431696F, 0.9049707F, 0.9056223F, 0.90627176F, 0.9069191F, 0.90756434F, 0.90820736F, 0.90884835F, 0.9094871F, 0.91012377F, 0.91075826F, 0.91139066F, 0.91202086F, 0.912649F, 0.9132749F, 0.91389865F, 0.9145203F, 0.9151398F, 0.9157571F, 0.9163723F, 0.9169853F, 0.91759616F, 0.91820484F, 0.9188114F, 0.9194158F, 0.92001796F, 0.92061806F, 0.9212159F, 0.92181164F, 0.9224052F, 0.9229965F, 0.9235858F, 0.92417276F, 0.9247576F, 0.9253403F, 0.9259208F, 0.9264991F, 0.92707527F, 0.92764926F, 0.928221F, 0.9287906F, 0.929358F, 0.92992324F, 0.93048626F, 0.9310471F, 0.93160576F, 0.9321622F, 0.9327165F, 0.93326855F, 0.93381846F, 0.9343661F, 0.9349116F, 0.93545485F, 0.93599594F, 0.9365348F, 0.9370715F, 0.937606F, 0.93813825F, 0.9386683F, 0.9391961F, 0.93972176F, 0.9402452F, 0.9407664F, 0.9412854F, 0.9418022F, 0.9423168F, 0.9428291F, 0.9433392F, 0.9438471F, 0.9443528F, 0.9448563F, 0.94535756F, 0.9458566F, 0.9463534F, 0.9468479F, 0.94734025F, 0.9478304F, 0.94831824F, 0.9488039F, 0.9492873F, 0.9497685F, 0.95024747F, 0.9507241F, 0.95119864F, 0.9516709F, 0.95214087F, 0.9526086F, 0.9530741F, 0.9535374F, 0.95399845F, 0.9544572F, 0.95491374F, 0.95536804F, 0.9558201F, 0.95626986F, 0.95671743F, 0.9571627F, 0.9576057F, 0.9580465F, 0.95848507F, 0.9589213F, 0.95935535F, 0.95978713F, 0.96021664F, 0.9606439F, 0.96106887F, 0.9614916F, 0.96191204F, 0.9623302F, 0.96274614F, 0.9631598F, 0.9635712F, 0.9639804F, 0.96438724F, 0.96479183F, 0.9651941F, 0.9655942F, 0.965992F, 0.96638745F, 0.9667807F, 0.96717167F, 0.96756035F, 0.96794677F, 0.96833086F, 0.96871275F, 0.9690923F, 0.9694696F, 0.9698446F, 0.97021735F, 0.9705878F, 0.9709559F, 0.9713218F, 0.9716854F, 0.97204673F, 0.97240573F, 0.97276247F, 0.9731169F, 0.973469F, 0.9738189F, 0.97416645F, 0.97451174F, 0.9748547F, 0.9751954F, 0.9755338F, 0.9758699F, 0.9762037F, 0.9765352F, 0.9768644F, 0.9771913F, 0.97751594F, 0.9778382F, 0.97815824F, 0.9784759F, 0.97879136F, 0.97910446F, 0.97941524F, 0.97972375F, 0.9800299F, 0.9803338F, 0.98063534F, 0.9809346F, 0.9812316F, 0.98152626F, 0.98181856F, 0.9821086F, 0.9823963F, 0.9826817F, 0.9829648F, 0.9832456F, 0.983524F, 0.98380023F, 0.98407406F, 0.98434556F, 0.9846148F, 0.98488164F, 0.9851462F, 0.9854085F, 0.9856684F, 0.98592603F, 0.9861813F, 0.9864343F, 0.9866849F, 0.9869333F, 0.9871793F, 0.98742294F, 0.98766434F, 0.98790336F, 0.9881401F, 0.9883745F, 0.9886065F, 0.9888363F, 0.9890637F, 0.98928875F, 0.9895115F, 0.98973197F, 0.98995006F, 0.9901658F, 0.9903792F, 0.99059033F, 0.9907991F, 0.99100554F, 0.9912097F, 0.99141145F, 0.9916109F, 0.991808F, 0.9920028F, 0.99219525F, 0.9923853F, 0.99257314F, 0.9927586F, 0.9929417F, 0.99312246F, 0.99330086F, 0.993477F, 0.99365073F, 0.99382216F, 0.9939912F, 0.99415797F, 0.99432236F, 0.9944844F, 0.99464417F, 0.9948015F, 0.99495655F, 0.99510926F, 0.9952596F, 0.99540764F, 0.9955533F, 0.9956966F, 0.9958376F, 0.99597627F, 0.9961126F, 0.9962465F, 0.9963781F, 0.9965074F, 0.9966343F, 0.9967589F, 0.9968811F, 0.997001F, 0.99711853F, 0.99723375F, 0.9973466F, 0.9974571F, 0.9975652F, 0.99767107F, 0.9977745F, 0.99787563F, 0.9979744F, 0.9980708F, 0.99816483F, 0.99825656F, 0.9983459F, 0.99843293F, 0.99851763F, 0.99859995F, 0.99867994F, 0.99875754F, 0.9988328F, 0.9989057F, 0.9989763F, 0.9990445F, 0.99911034F, 0.9991739F, 0.99923503F, 0.99929386F, 0.9993503F, 0.99940443F, 0.99945617F, 0.9995056F, 0.99955267F, 0.9995974F, 0.9996397F, 0.9996797F, 0.99971735F, 0.99975264F, 0.9997856F, 0.9998162F, 0.99984443F, 0.9998703F, 0.99989384F, 0.999915F, 0.99993384F, 0.9999503F, 0.9999644F, 0.99997616F, 0.9999856F, 0.99999267F, 0.9999974F, 0.9999997F };

    float[] SINE_128 = new float[] { 0.0061358847F, 0.01840673F, 0.030674804F, 0.04293826F, 0.055195246F, 0.06744392F, 0.07968244F, 0.091908954F, 0.10412163F, 0.11631863F, 0.1284981F, 0.14065824F, 0.15279719F, 0.16491312F, 0.17700422F, 0.18906866F, 0.20110464F, 0.21311031F, 0.22508392F, 0.2370236F, 0.24892761F, 0.2607941F, 0.27262136F, 0.28440753F, 0.2961509F, 0.30784965F, 0.31950203F, 0.3311063F, 0.34266073F, 0.35416353F, 0.36561298F, 0.37700742F, 0.38834503F, 0.3996242F, 0.41084316F, 0.42200026F, 0.43309382F, 0.44412214F, 0.45508358F, 0.4659765F, 0.47679922F, 0.48755017F, 0.49822766F, 0.50883013F, 0.519356F, 0.52980363F, 0.54017144F, 0.55045795F, 0.56066155F, 0.57078075F, 0.58081394F, 0.5907597F, 0.60061646F, 0.6103828F, 0.6200572F, 0.62963825F, 0.63912445F, 0.6485144F, 0.6578067F, 0.66699994F, 0.6760927F, 0.6850837F, 0.69397146F, 0.70275474F, 0.7114322F, 0.72000253F, 0.72846437F, 0.7368166F, 0.74505776F, 0.7531868F, 0.7612024F, 0.76910335F, 0.7768885F, 0.78455657F, 0.79210657F, 0.79953724F, 0.8068476F, 0.8140363F, 0.8211025F, 0.82804507F, 0.8348629F, 0.841555F, 0.84812033F, 0.854558F, 0.86086696F, 0.86704624F, 0.873095F, 0.8790122F, 0.8847971F, 0.89044875F, 0.89596623F, 0.9013488F, 0.9065957F, 0.91170603F, 0.9166791F, 0.92151403F, 0.9262102F, 0.93076694F, 0.9351835F, 0.9394592F, 0.94359344F, 0.9475856F, 0.951435F, 0.9551412F, 0.95870346F, 0.9621214F, 0.96539444F, 0.9685221F, 0.9715039F, 0.97433937F, 0.97702813F, 0.9795698F, 0.9819639F, 0.9842101F, 0.9863081F, 0.9882576F, 0.9900582F, 0.99170977F, 0.9932119F, 0.9945646F, 0.9957674F, 0.9968203F, 0.99772304F, 0.99847555F, 0.99907774F, 0.9995294F, 0.9998306F, 0.99998116F };

    float[] SINE_960 = new float[] { 8.18123E-4F, 0.0024543668F, 0.004090604F, 0.00572683F, 0.0073630414F, 0.008999232F, 0.010635399F, 0.012271538F, 0.013907644F, 0.015543712F, 0.017179739F, 0.01881572F, 0.020451652F, 0.022087527F, 0.023723343F, 0.025359096F, 0.026994782F, 0.028630394F, 0.030265931F, 0.031901386F, 0.03353676F, 0.035172038F, 0.036807224F, 0.03844231F, 0.040077295F, 0.041712172F, 0.043346938F, 0.044981588F, 0.046616115F, 0.04825052F, 0.049884796F, 0.051518936F, 0.05315294F, 0.0547868F, 0.056420516F, 0.05805408F, 0.059687488F, 0.061320737F, 0.06295382F, 0.064586736F, 0.06621948F, 0.06785204F, 0.06948443F, 0.071116626F, 0.07274863F, 0.07438044F, 0.07601206F, 0.07764347F, 0.07927467F, 0.08090566F, 0.08253644F, 0.08416699F, 0.08579731F, 0.08742741F, 0.089057274F, 0.090686895F, 0.09231628F, 0.093945414F, 0.0955743F, 0.09720293F, 0.09883129F, 0.1004594F, 0.10208723F, 0.10371479F, 0.10534207F, 0.10696907F, 0.10859578F, 0.110222206F, 0.11184833F, 0.11347417F, 0.11509969F, 0.11672491F, 0.11834981F, 0.1199744F, 0.12159866F, 0.123222604F, 0.12484621F, 0.1264695F, 0.12809242F, 0.12971503F, 0.13133727F, 0.13295917F, 0.1345807F, 0.13620189F, 0.1378227F, 0.13944314F, 0.14106323F, 0.14268291F, 0.14430223F, 0.14592116F, 0.14753969F, 0.14915784F, 0.15077558F, 0.15239291F, 0.15400985F, 0.15562636F, 0.15724246F, 0.15885815F, 0.16047339F, 0.16208823F, 0.1637026F, 0.16531657F, 0.16693008F, 0.16854315F, 0.17015575F, 0.1717679F, 0.1733796F, 0.17499083F, 0.1766016F, 0.1782119F, 0.17982171F, 0.18143104F, 0.18303989F, 0.18464825F, 0.18625611F, 0.18786347F, 0.18947034F, 0.19107668F, 0.19268253F, 0.19428785F, 0.19589266F, 0.19749694F, 0.19910069F, 0.20070392F, 0.2023066F, 0.20390874F, 0.20551033F, 0.20711137F, 0.20871186F, 0.2103118F, 0.21191117F, 0.21350996F, 0.2151082F, 0.21670584F, 0.21830292F, 0.21989942F, 0.22149532F, 0.22309062F, 0.22468533F, 0.22627944F, 0.22787294F, 0.22946583F, 0.2310581F, 0.23264977F, 0.2342408F, 0.23583122F, 0.23742099F, 0.23901014F, 0.24059863F, 0.24218649F, 0.2437737F, 0.24536026F, 0.24694616F, 0.2485314F, 0.25011596F, 0.25169986F, 0.2532831F, 0.25486565F, 0.25644752F, 0.25802872F, 0.2596092F, 0.261189F, 0.2627681F, 0.26434648F, 0.2659242F, 0.26750115F, 0.26907742F, 0.27065295F, 0.27222776F, 0.27380186F, 0.2753752F, 0.27694783F, 0.2785197F, 0.2800908F, 0.28166118F, 0.2832308F, 0.2847997F, 0.28636777F, 0.2879351F, 0.2895017F, 0.29106748F, 0.2926325F, 0.29419672F, 0.29576015F, 0.2973228F, 0.29888466F, 0.3004457F, 0.30200595F, 0.30356538F, 0.305124F, 0.3066818F, 0.3082388F, 0.30979496F, 0.31135032F, 0.3129048F, 0.31445846F, 0.31601128F, 0.31756327F, 0.3191144F, 0.32066464F, 0.32221407F, 0.32376263F, 0.3253103F, 0.3268571F, 0.32840303F, 0.3299481F, 0.33149227F, 0.33303556F, 0.33457795F, 0.33611944F, 0.33766004F, 0.33919972F, 0.3407385F, 0.3422764F, 0.34381336F, 0.34534937F, 0.3468845F, 0.34841868F, 0.34995192F, 0.35148424F, 0.3530156F, 0.35454604F, 0.35607553F, 0.35760406F, 0.3591316F, 0.36065823F, 0.36218387F, 0.36370853F, 0.36523223F, 0.36675495F, 0.3682767F, 0.36979744F, 0.3713172F, 0.37283596F, 0.37435374F, 0.3758705F, 0.37738624F, 0.378901F, 0.38041475F, 0.38192746F, 0.38343915F, 0.38494983F, 0.38645947F, 0.38796806F, 0.3894756F, 0.39098215F, 0.39248762F, 0.39399204F, 0.3954954F, 0.39699772F, 0.39849895F, 0.39999914F, 0.40149826F, 0.4029963F, 0.40449324F, 0.4059891F, 0.4074839F, 0.4089776F, 0.4104702F, 0.4119617F, 0.4134521F, 0.41494137F, 0.41642955F, 0.41791663F, 0.41940257F, 0.42088738F, 0.4223711F, 0.42385367F, 0.42533508F, 0.4268154F, 0.42829454F, 0.42977253F, 0.43124938F, 0.43272507F, 0.4341996F, 0.435673F, 0.4371452F, 0.43861625F, 0.4400861F, 0.44155478F, 0.44302228F, 0.4444886F, 0.44595373F, 0.44741768F, 0.4488804F, 0.45034194F, 0.45180228F, 0.4532614F, 0.4547193F, 0.45617598F, 0.45763147F, 0.4590857F, 0.46053872F, 0.4619905F, 0.46344104F, 0.46489033F, 0.4663384F, 0.4677852F, 0.46923077F, 0.47067505F, 0.4721181F, 0.4735599F, 0.47500038F, 0.47643963F, 0.4778776F, 0.47931427F, 0.48074967F, 0.48218378F, 0.4836166F, 0.48504812F, 0.48647836F, 0.48790726F, 0.48933488F, 0.4907612F, 0.4921862F, 0.49360988F, 0.49503222F, 0.49645326F, 0.49787295F, 0.49929133F, 0.50070834F, 0.502124F, 0.50353837F, 0.50495136F, 0.50636303F, 0.50777334F, 0.5091823F, 0.51058984F, 0.51199603F, 0.51340085F, 0.5148043F, 0.5162064F, 0.5176071F, 0.5190064F, 0.5204043F, 0.5218008F, 0.523196F, 0.52458966F, 0.525982F, 0.5273729F, 0.5287624F, 0.53015053F, 0.5315372F, 0.53292245F, 0.5343062F, 0.53568864F, 0.5370696F, 0.5384491F, 0.53982717F, 0.5412038F, 0.542579F, 0.5439527F, 0.545325F, 0.5466958F, 0.5480651F, 0.54943305F, 0.5507994F, 0.5521644F, 0.55352783F, 0.5548898F, 0.5562503F, 0.5576093F, 0.5589668F, 0.5603228F, 0.56167734F, 0.5630303F, 0.56438184F, 0.5657318F, 0.56708026F, 0.56842726F, 0.56977266F, 0.57111657F, 0.572459F, 0.5737998F, 0.5751391F, 0.5764769F, 0.5778131F, 0.5791478F, 0.58048093F, 0.5818125F, 0.5831425F, 0.584471F, 0.58579785F, 0.58712316F, 0.5884469F, 0.5897691F, 0.5910897F, 0.5924087F, 0.59372616F, 0.595042F, 0.5963562F, 0.5976689F, 0.59897995F, 0.60028934F, 0.6015972F, 0.6029034F, 0.60420805F, 0.60551107F, 0.6068124F, 0.60811216F, 0.6094103F, 0.61070675F, 0.61200166F, 0.61329484F, 0.6145864F, 0.6158764F, 0.6171646F, 0.61845124F, 0.61973625F, 0.62101954F, 0.62230116F, 0.6235812F, 0.6248595F, 0.6261361F, 0.62741107F, 0.6286844F, 0.629956F, 0.6312259F, 0.63249415F, 0.63376063F, 0.6350255F, 0.63628864F, 0.63755006F, 0.6388098F, 0.6400678F, 0.6413241F, 0.64257866F, 0.64383155F, 0.6450827F, 0.6463321F, 0.6475798F, 0.6488257F, 0.65006995F, 0.6513124F, 0.65255314F, 0.65379214F, 0.65502936F, 0.65626484F, 0.65749854F, 0.6587305F, 0.6599607F, 0.66118914F, 0.6624158F, 0.6636407F, 0.66486377F, 0.6660851F, 0.66730464F, 0.6685224F, 0.66973835F, 0.67095256F, 0.6721649F, 0.6733755F, 0.67458427F, 0.67579126F, 0.6769964F, 0.67819977F, 0.6794013F, 0.680601F, 0.6817989F, 0.68299496F, 0.6841892F, 0.6853816F, 0.6865722F, 0.6877609F, 0.6889478F, 0.69013286F, 0.691316F, 0.6924974F, 0.6936769F, 0.6948545F, 0.6960303F, 0.69720423F, 0.69837624F, 0.6995464F, 0.7007147F, 0.7018812F, 0.7030457F, 0.7042084F, 0.7053692F, 0.70652807F, 0.70768505F, 0.70884013F, 0.70999336F, 0.7111447F, 0.71229404F, 0.71344155F, 0.71458715F, 0.71573085F, 0.7168726F, 0.7180124F, 0.71915036F, 0.7202863F, 0.7214204F, 0.72255254F, 0.7236827F, 0.72481096F, 0.7259373F, 0.7270617F, 0.7281841F, 0.72930455F, 0.7304231F, 0.73153967F, 0.7326543F, 0.7337669F, 0.7348776F, 0.73598635F, 0.7370931F, 0.73819786F, 0.73930067F, 0.74040145F, 0.7415003F, 0.74259716F, 0.743692F, 0.7447849F, 0.7458758F, 0.74696463F, 0.7480515F, 0.7491364F, 0.7502193F, 0.75130016F, 0.752379F, 0.7534558F, 0.75453067F, 0.75560343F, 0.75667423F, 0.75774294F, 0.7588097F, 0.7598744F, 0.76093704F, 0.76199764F, 0.7630562F, 0.7641128F, 0.76516724F, 0.76621974F, 0.7672701F, 0.7683184F, 0.7693647F, 0.7704089F, 0.7714511F, 0.77249116F, 0.77352923F, 0.77456516F, 0.77559906F, 0.7766308F, 0.77766055F, 0.7786882F, 0.77971375F, 0.7807372F, 0.7817586F, 0.7827779F, 0.78379506F, 0.7848102F, 0.78582317F, 0.78683406F, 0.7878428F, 0.7888495F, 0.78985405F, 0.7908565F, 0.7918568F, 0.792855F, 0.79385114F, 0.7948451F, 0.7958369F, 0.7968266F, 0.7978142F, 0.7987996F, 0.7997829F, 0.800764F, 0.80174303F, 0.8027199F, 0.8036946F, 0.8046672F, 0.8056376F, 0.8066058F, 0.8075719F, 0.8085358F, 0.8094976F, 0.81045717F, 0.8114146F, 0.8123699F, 0.81332296F, 0.81427383F, 0.81522256F, 0.8161691F, 0.81711346F, 0.8180556F, 0.8189956F, 0.81993335F, 0.82086897F, 0.8218024F, 0.8227335F, 0.8236625F, 0.8245893F, 0.8255139F, 0.8264362F, 0.8273564F, 0.8282743F, 0.82919F, 0.8301035F, 0.8310148F, 0.83192384F, 0.83283067F, 0.8337353F, 0.83463764F, 0.83553773F, 0.8364357F, 0.8373313F, 0.8382247F, 0.83911586F, 0.8400048F, 0.8408915F, 0.8417759F, 0.84265804F, 0.8435379F, 0.8444156F, 0.84529096F, 0.8461641F, 0.84703493F, 0.84790355F, 0.84876984F, 0.84963393F, 0.8504957F, 0.8513552F, 0.8522124F, 0.85306734F, 0.85392F, 0.85477036F, 0.8556184F, 0.8564642F, 0.85730773F, 0.85814893F, 0.8589878F, 0.8598244F, 0.8606587F, 0.8614907F, 0.8623204F, 0.8631478F, 0.86397284F, 0.8647956F, 0.865616F, 0.86643416F, 0.86724997F, 0.86806345F, 0.8688746F, 0.86968344F, 0.87048995F, 0.87129414F, 0.87209594F, 0.8728955F, 0.87369263F, 0.87448746F, 0.87527996F, 0.8760701F, 0.8768579F, 0.87764335F, 0.87842643F, 0.8792072F, 0.8799856F, 0.8807616F, 0.8815353F, 0.88230664F, 0.8830756F, 0.8838422F, 0.8846064F, 0.8853683F, 0.88612777F, 0.88688487F, 0.88763964F, 0.888392F, 0.889142F, 0.8898896F, 0.89063483F, 0.8913776F, 0.8921181F, 0.8928562F, 0.8935918F, 0.89432514F, 0.895056F, 0.8957845F, 0.8965106F, 0.89723426F, 0.8979556F, 0.8986745F, 0.89939094F, 0.900105F, 0.9008167F, 0.9015259F, 0.90223277F, 0.9029372F, 0.9036392F, 0.9043388F, 0.905036F, 0.90573066F, 0.90642303F, 0.9071129F, 0.9078004F, 0.9084854F, 0.909168F, 0.90984815F, 0.91052586F, 0.9112012F, 0.911874F, 0.9125444F, 0.9132124F, 0.9138779F, 0.914541F, 0.9152016F, 0.9158598F, 0.9165155F, 0.9171688F, 0.9178196F, 0.91846794F, 0.9191139F, 0.9197573F, 0.92039824F, 0.9210368F, 0.9216728F, 0.9223064F, 0.9229375F, 0.92356616F, 0.9241923F, 0.924816F, 0.9254372F, 0.92605597F, 0.9266722F, 0.92728597F, 0.9278973F, 0.9285061F, 0.9291124F, 0.9297162F, 0.9303176F, 0.9309164F, 0.93151283F, 0.9321067F, 0.9326981F, 0.9332869F, 0.9338733F, 0.9344572F, 0.93503857F, 0.93561745F, 0.9361938F, 0.93676764F, 0.937339F, 0.9379079F, 0.9384742F, 0.939038F, 0.93959934F, 0.9401581F, 0.94071436F, 0.94126815F, 0.94181937F, 0.9423681F, 0.94291425F, 0.94345796F, 0.94399905F, 0.9445377F, 0.9450738F, 0.9456073F, 0.9461383F, 0.94666684F, 0.9471928F, 0.94771624F, 0.94823706F, 0.94875544F, 0.94927126F, 0.9497845F, 0.9502952F, 0.9508034F, 0.951309F, 0.9518121F, 0.95231265F, 0.9528106F, 0.953306F, 0.95379895F, 0.95428926F, 0.954777F, 0.95526224F, 0.95574486F, 0.956225F, 0.95670253F, 0.9571775F, 0.95764995F, 0.95811975F, 0.95858705F, 0.9590518F, 0.9595139F, 0.9599735F, 0.9604305F, 0.960885F, 0.96133685F, 0.96178615F, 0.9622328F, 0.962677F, 0.96311855F, 0.96355754F, 0.96399397F, 0.96442777F, 0.964859F, 0.9652877F, 0.96571374F, 0.96613723F, 0.96655816F, 0.96697646F, 0.9673922F, 0.9678053F, 0.9682159F, 0.9686238F, 0.9690292F, 0.969432F, 0.9698321F, 0.97022974F, 0.9706247F, 0.97101706F, 0.9714069F, 0.971794F, 0.9721786F, 0.9725606F, 0.97293997F, 0.9733167F, 0.97369087F, 0.97406244F, 0.9744314F, 0.9747977F, 0.97516143F, 0.9755226F, 0.97588104F, 0.97623694F, 0.9765902F, 0.9769409F, 0.97728896F, 0.9776344F, 0.97797716F, 0.9783174F, 0.9786549F, 0.9789899F, 0.97932225F, 0.9796519F, 0.97997904F, 0.9803035F, 0.98062533F, 0.9809446F, 0.98126113F, 0.98157513F, 0.98188645F, 0.98219514F, 0.9825012F, 0.98280466F, 0.9831055F, 0.9834037F, 0.9836992F, 0.98399216F, 0.98428243F, 0.9845701F, 0.98485506F, 0.98513746F, 0.9854172F, 0.9856943F, 0.9859687F, 0.98624057F, 0.98650974F, 0.9867763F, 0.98704016F, 0.9873014F, 0.98756003F, 0.987816F, 0.9880693F, 0.98832F, 0.988568F, 0.9888134F, 0.9890561F, 0.9892962F, 0.98953366F, 0.98976845F, 0.9900006F, 0.9902301F, 0.99045694F, 0.9906811F, 0.99090266F, 0.99112153F, 0.9913377F, 0.99155134F, 0.9917622F, 0.9919705F, 0.9921761F, 0.99237907F, 0.99257934F, 0.992777F, 0.99297196F, 0.9931643F, 0.99335396F, 0.99354094F, 0.9937253F, 0.993907F, 0.99408597F, 0.99426234F, 0.994436F, 0.9946071F, 0.9947755F, 0.9949412F, 0.9951042F, 0.9952646F, 0.9954223F, 0.99557734F, 0.99572974F, 0.9958795F, 0.9960265F, 0.9961709F, 0.9963126F, 0.9964517F, 0.99658805F, 0.99672174F, 0.9968528F, 0.9969812F, 0.9971069F, 0.99722993F, 0.99735034F, 0.997468F, 0.99758303F, 0.9976954F, 0.99780506F, 0.9979121F, 0.9980164F, 0.9981181F, 0.9982171F, 0.9983134F, 0.99840707F, 0.9984981F, 0.99858636F, 0.998672F, 0.998755F, 0.99883527F, 0.9989129F, 0.9989878F, 0.9990601F, 0.99912965F, 0.9991966F, 0.99926084F, 0.99932235F, 0.99938124F, 0.9994375F, 0.99949104F, 0.9995419F, 0.99959004F, 0.9996356F, 0.99967843F, 0.99971855F, 0.99975604F, 0.99979085F, 0.999823F, 0.9998524F, 0.9998792F, 0.99990326F, 0.9999247F, 0.99994344F, 0.9999595F, 0.9999729F, 0.9999836F, 0.99999166F, 0.99999696F, 0.99999964F };

    float[] SINE_120 = new float[] { 0.006544938F, 0.019633692F, 0.032719083F, 0.045798868F, 0.058870804F, 0.07193265F, 0.08498218F, 0.09801714F, 0.11103531F, 0.12403445F, 0.13701235F, 0.14996676F, 0.16289547F, 0.17579629F, 0.18866697F, 0.20150532F, 0.21430916F, 0.22707626F, 0.23980446F, 0.25249156F, 0.26513544F, 0.27773383F, 0.29028466F, 0.30278578F, 0.315235F, 0.3276302F, 0.33996925F, 0.35225004F, 0.3644705F, 0.3766285F, 0.38872197F, 0.40074882F, 0.41270703F, 0.42459452F, 0.43640924F, 0.4481492F, 0.45981237F, 0.47139674F, 0.48290035F, 0.4943212F, 0.5056574F, 0.5169069F, 0.5280678F, 0.5391383F, 0.5501164F, 0.5610002F, 0.57178795F, 0.5824777F, 0.59306765F, 0.6035559F, 0.61394083F, 0.62422055F, 0.6343933F, 0.64445734F, 0.65441096F, 0.66425246F, 0.6739801F, 0.6835923F, 0.69308734F, 0.7024637F, 0.71171963F, 0.72085357F, 0.72986406F, 0.7387495F, 0.74750835F, 0.7561391F, 0.7646403F, 0.77301043F, 0.78124815F, 0.78935206F, 0.79732066F, 0.80515265F, 0.81284666F, 0.82040143F, 0.82781565F, 0.83508795F, 0.8422172F, 0.84920216F, 0.8560416F, 0.8627344F, 0.8692793F, 0.8756753F, 0.8819213F, 0.8880161F, 0.8939588F, 0.89974827F, 0.90538365F, 0.9108638F, 0.91618794F, 0.9213551F, 0.92636436F, 0.9312149F, 0.93590593F, 0.94043654F, 0.94480604F, 0.94901365F, 0.95305866F, 0.95694035F, 0.9606581F, 0.96421117F, 0.9675991F, 0.9708212F, 0.97387695F, 0.9767659F, 0.9794874F, 0.9820411F, 0.98442656F, 0.9866433F, 0.98869103F, 0.99056935F, 0.9922779F, 0.99381644F, 0.9951847F, 0.9963825F, 0.99740946F, 0.9982656F, 0.99895066F, 0.9994646F, 0.99980724F, 0.9999786F };
}