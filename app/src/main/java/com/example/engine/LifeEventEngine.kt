package com.example.engine

import com.example.model.*
import kotlin.random.Random

object LifeEventEngine {

    val scenarios: List<GameScenario> = listOf(
        // ==========================================
        // 👶 الطفولة (0 - 12 سنة)
        // ==========================================
        GameScenario(
            id = "childhood_vase",
            stage = LifeStage.CHILDHOOD,
            titleAr = "مزهرية العائلة العتيقة 🏺",
            storyAr = "أثناء محاولتك تقليد الأبطال الخارقين والقفز من فوق الأريكة، اصطدمت بمزهرية جدتك باهظة الثمن فتهشمت إلى مئات القطع ودوّى الصوت في أرجاء المنزل!",
            emoji = "🏺",
            minAge = 4,
            maxAge = 10,
            choices = listOf(
                ScenarioChoice(
                    id = "vase_confess",
                    textAr = "الاعتراف بشجاعة والاعتذار بدموع بريئة",
                    outcomeDescriptionAr = "قدّر والداك صراحتك رغم الحزن على المزهرية. عوقبت بحرمان من الشاشات لمدة أسبوع فقط ولكنك كسبت ثقتهم.",
                    statChange = StatChange(health = 0, intelligence = 5, relationships = 15, wealth = 0, happiness = -5),
                    milestoneTitle = "درس الصدق الأول"
                ),
                ScenarioChoice(
                    id = "vase_blame_cat",
                    textAr = "اتهام القطة أو أخيك الصغير ببرود أعصاب!",
                    outcomeDescriptionAr = "نجوت مؤقتاً، لكن نظرات الشك لم تفارق والدتك، وشعرت بوخز خفيف في ضميرك الصغير.",
                    statChange = StatChange(health = 0, intelligence = 10, relationships = -15, wealth = 0, happiness = 5)
                ),
                ScenarioChoice(
                    id = "vase_glue",
                    textAr = "محاولة لصقها بغراء مدرسي وإعادتها للرف",
                    outcomeDescriptionAr = "سقطت المزهرية مجدداً بعد دقيقتين وانهارت خطتك العبقرية. ضحك الجميع على سذاجتك واشتروا مزهرية بلاستيكية رخيصة.",
                    statChange = StatChange(health = 0, intelligence = 10, relationships = 5, wealth = -50, happiness = 5)
                )
            )
        ),
        GameScenario(
            id = "childhood_first_day",
            stage = LifeStage.CHILDHOOD,
            titleAr = "أول يوم دراسي والمقعد الأول 🎒",
            storyAr = "دخلت الفصل المدرسي لأول مرة. هناك مقعد فارغ في الصف الأول بجانب التلميذ النجيب، ومقعد في الصف الخلفي بجانب زعيم المشاغبين الذي يوزع الحلوى خلسة!",
            emoji = "🎒",
            minAge = 6,
            maxAge = 8,
            choices = listOf(
                ScenarioChoice(
                    id = "first_seat_front",
                    textAr = "الجلوس في المقعد الأمامي والمذاكرة بجد",
                    outcomeDescriptionAr = "أصبحت المفضل لدى المعلم، واكتسبت مهارات دراسية مبكرة ومثابرة عالية.",
                    statChange = StatChange(health = 0, intelligence = 15, relationships = 5, wealth = 0, happiness = -5),
                    milestoneTitle = "تلميذ نموذجي"
                ),
                ScenarioChoice(
                    id = "first_seat_back",
                    textAr = "الجلوس في الصف الأخير ومشاركة المشاغبين الضحك",
                    outcomeDescriptionAr = "أصبحت شخصاً محبوباً جداً وصاحب نكتة في الفصل، رغم توبيخ المعلم المتكرر.",
                    statChange = StatChange(health = 0, intelligence = -5, relationships = 15, wealth = 0, happiness = 15),
                    milestoneTitle = "روح المرح في الفصل"
                ),
                ScenarioChoice(
                    id = "first_seat_solo",
                    textAr = "الجلوس وحيداً وقراءة كتب المغامرات والرسوم المصورة",
                    outcomeDescriptionAr = "بنيت عالماً خيالياً واسعاً وصرت واسع الأفق، وإن كنت هادئاً قليلاً.",
                    statChange = StatChange(health = 0, intelligence = 10, relationships = -5, wealth = 0, happiness = 10)
                )
            )
        ),
        GameScenario(
            id = "childhood_candy_theft",
            stage = LifeStage.CHILDHOOD,
            titleAr = "إغراء شوكولاتة البقالة 🍫",
            storyAr = "في بقالة الحي القديمة، انزلقت قطعة شوكولاتة فاخرة في جيب معطفك دون قصد، ولم ينتبه البائع العجوز المنهمك في قراءة الجريدة.",
            emoji = "🍫",
            minAge = 7,
            maxAge = 11,
            choices = listOf(
                ScenarioChoice(
                    id = "candy_return",
                    textAr = "إعادتها فوراً للبائع مع اعتذار مهذب",
                    outcomeDescriptionAr = "ابتسم البائع وأعجب بأمانتك فأهداك قطعتين مجاناً ودعا لك بالبركة!",
                    statChange = StatChange(health = 0, intelligence = 5, relationships = 15, wealth = 0, happiness = 10),
                    milestoneTitle = "وسام الأمانة المبكرة"
                ),
                ScenarioChoice(
                    id = "candy_eat",
                    textAr = "أكلها سريعاً خلف الجدار قبل أن يراك أحد",
                    outcomeDescriptionAr = "كان طعمها لذيذاً وممزوجاً بالأدرينالين، لكنك ظللت تتلفت يمنة ويسرة خوفاً من صفارات الشرطة لأيام!",
                    statChange = StatChange(health = -5, intelligence = 5, relationships = -10, wealth = 0, happiness = 5)
                ),
                ScenarioChoice(
                    id = "candy_tell_dad",
                    textAr = "إخبار والدك ليدفع ثمنها للبائع",
                    outcomeDescriptionAr = "دفع والدك الثمن برحابة صدر، وألقى عليك درساً في الشرف والقيم الإنسانية ظل راسخاً في ذهنك.",
                    statChange = StatChange(health = 0, intelligence = 10, relationships = 15, wealth = -20, happiness = 5)
                )
            )
        ),
        GameScenario(
            id = "childhood_hobby",
            stage = LifeStage.CHILDHOOD,
            titleAr = "اختيار الهواية الأولى ⚽",
            storyAr = "سألك والداك في بداية الإجازة الصيفية: 'ما هو النشاط الذي ترغب في تعلمه وتطوير نفسك فيه هذا العام؟'",
            emoji = "🎨",
            minAge = 8,
            maxAge = 12,
            choices = listOf(
                ScenarioChoice(
                    id = "hobby_football",
                    textAr = "الانضمام لأكاديمية كرة القدم للناشئين",
                    outcomeDescriptionAr = "تحسنت لياقتك البدنية بشكل مذهل وأصبحت قائداً لفريق الحي وسجلت أهدافاً حاسمة!",
                    statChange = StatChange(health = 20, intelligence = 0, relationships = 15, wealth = -100, happiness = 15),
                    milestoneTitle = "نجم كرة القدم الواعد"
                ),
                ScenarioChoice(
                    id = "hobby_piano",
                    textAr = "تعلم العزف على الآلات الموسيقية والفنون",
                    outcomeDescriptionAr = "ارتقى ذوقك الفني وصرت متميزاً في الحفلات المدرسية بنغمات رقيقة وهادئة.",
                    statChange = StatChange(health = 0, intelligence = 15, relationships = 10, wealth = -200, happiness = 15),
                    milestoneTitle = "الموهبة الموسيقية"
                ),
                ScenarioChoice(
                    id = "hobby_gaming",
                    textAr = "قضاء الإجازة في ألعاب الفيديو وبناء عوالم رقمية",
                    outcomeDescriptionAr = "طوّرت سرعة ردود أفعالك وتفكيرك الاستراتيجي، مع قليل من إرهاق العينين وسهر الليل.",
                    statChange = StatChange(health = -5, intelligence = 15, relationships = -5, wealth = -50, happiness = 20)
                )
            )
        ),
        GameScenario(
            id = "childhood_eid_money",
            stage = LifeStage.CHILDHOOD,
            titleAr = "غنائم العيد (العيدية) 💰",
            storyAr = "بعد جولة طويلة بين بيوت الأقارب في صباح العيد، عدت بجيوب منتفخة بنقود العيدية الورقية الجديدة اللامعة!",
            emoji = "🎉",
            minAge = 9,
            maxAge = 12,
            choices = listOf(
                ScenarioChoice(
                    id = "eid_piggy_bank",
                    textAr = "وضعها كاملة في الحصالة وبدء التوفير المنظم",
                    outcomeDescriptionAr = "تعلّمت الانضباط المالي في سن مبكرة وجمعت رأس مال طفولي محترم.",
                    statChange = StatChange(health = 0, intelligence = 15, relationships = 5, wealth = 350, happiness = 5),
                    milestoneTitle = "المستثمر الصغير"
                ),
                ScenarioChoice(
                    id = "eid_spend_all",
                    textAr = "إنفاقها بالكامل على المفرقعات والحلويات وألعاب الملاهي",
                    outcomeDescriptionAr = "عشت يوماً أسطورياً من الفرح الصاخب مع أبناء الجيران، رغم مغص خفيف من كثرة السكاكر!",
                    statChange = StatChange(health = -5, intelligence = -5, relationships = 15, wealth = 50, happiness = 25)
                ),
                ScenarioChoice(
                    id = "eid_mom_safe",
                    textAr = "تسليمها للوالدة لكي 'تحفظها لك للمستقبل'",
                    outcomeDescriptionAr = "كسبت رضا الوالدة اللامتناهي ودعواتها الطيبة، رغم أنك لم تشاهد تلك النقود مجدداً أبداً!",
                    statChange = StatChange(health = 0, intelligence = 5, relationships = 20, wealth = 0, happiness = -5)
                )
            )
        ),

        // ==========================================
        // 👦 المراهقة (13 - 19 سنة)
        // ==========================================
        GameScenario(
            id = "teen_hair_rebellion",
            stage = LifeStage.ADOLESCENCE,
            titleAr = "قصة الشعر الغريبة والتمرد ✂️",
            storyAr = "قررت إثبات استقلاليتك بتجربة قصة شعر مبتكرة وغريبة جداً رأيتها على أحد المشاهير، لكن مظهرها صدم العائلة والمدرسة!",
            emoji = "💈",
            minAge = 13,
            maxAge = 16,
            choices = listOf(
                ScenarioChoice(
                    id = "hair_own_it",
                    textAr = "المواجهة والذهاب بها للمدرسة بكل ثقة",
                    outcomeDescriptionAr = "لفتّ جميع الأنظار وأصبحت حديث الساعة بين الزملاء، وإن كان الناظر قد وجه لك إنذاراً حازماً.",
                    statChange = StatChange(health = 0, intelligence = -5, relationships = -10, wealth = -30, happiness = 20),
                    milestoneTitle = "المتمرد الواثق"
                ),
                ScenarioChoice(
                    id = "hair_cap",
                    textAr = "ارتداء قبعة لإخفائها حتى ينمو الشعر من جديد",
                    outcomeDescriptionAr = "تجنبت الصدام والمواقف المحرجة، وقضيت أشهراً تفضل فيها البقاء في الظل.",
                    statChange = StatChange(health = 0, intelligence = 10, relationships = 5, wealth = 0, happiness = -10)
                ),
                ScenarioChoice(
                    id = "hair_convince_parents",
                    textAr = "إقناع الوالد بحكمة وهدوء أنها تعبير فني مؤقت",
                    outcomeDescriptionAr = "أظهرت نضجاً حوارياً مبهراً أثار إعجاب والدك فتفهم الأمر وتبادلتما الضحك على النتيجة.",
                    statChange = StatChange(health = 0, intelligence = 15, relationships = 15, wealth = 0, happiness = 15)
                )
            )
        ),
        GameScenario(
            id = "teen_high_school_exam",
            stage = LifeStage.ADOLESCENCE,
            titleAr = "كابوس امتحانات الثانوية العامة 📚",
            storyAr = "الليلة التي تسبق الامتحان المصيري للثانوية العامة وأنت تدرك أن نصف المنهج لم يُفتح بعد والوقت يداهمك!",
            emoji = "📝",
            minAge = 16,
            maxAge = 18,
            choices = listOf(
                ScenarioChoice(
                    id = "exam_all_nighter",
                    textAr = "سهر الليل كاملاً مع أكواب القهوة المركزة والتركيز المطلق",
                    outcomeDescriptionAr = "استوعبت أهم النقاط وحققت علامة ممتازة دخلت بها الجامعة، رغم صداع حاد لازَمك ليومين.",
                    statChange = StatChange(health = -15, intelligence = 20, relationships = 0, wealth = 0, happiness = 10),
                    milestoneTitle = "تجاوز عنق الزجاجة"
                ),
                ScenarioChoice(
                    id = "exam_sleep",
                    textAr = "النوم باكراً والاعتماد على الذكاء الفطري وما تبقى في الذاكرة",
                    outcomeDescriptionAr = "استيقظت بذهن صافٍ، نجحت بنتيجة مقبولة وحافظت على صحتك النفسية وسلامك الداخلي.",
                    statChange = StatChange(health = 10, intelligence = 5, relationships = 5, wealth = 0, happiness = 10)
                ),
                ScenarioChoice(
                    id = "exam_cheat_sheet",
                    textAr = "إعداد ملخصات صغيرة للمجازفة بالغش (خيار خطير!)",
                    outcomeDescriptionAr = "تم رصد الورقة من قبل المراقب وسُحبت ورقتك مع توبيخ عاصف وكدت تُحرم من الدورة!",
                    statChange = StatChange(health = -10, intelligence = -10, relationships = -20, wealth = 0, happiness = -25)
                )
            )
        ),
        GameScenario(
            id = "teen_first_crush",
            stage = LifeStage.ADOLESCENCE,
            titleAr = "رسالة الإعجاب الأولى 💌",
            storyAr = "وصلتك رسالة مكتوبة بخط يد أنيق من شخص يتردد على نفس المكتبة العامة، يعبر فيها عن إعجابه بلباقتك وهدوءك.",
            emoji = "💌",
            minAge = 15,
            maxAge = 18,
            choices = listOf(
                ScenarioChoice(
                    id = "crush_romantic_reply",
                    textAr = "الرد بلباقة ودعوة لمشروب ساخن والتحدث عن الكتب",
                    outcomeDescriptionAr = "عشت تجربة صداقة راقية مليئة بالمشاعر الصادقة والنقاشات الفكرية الجميلة.",
                    statChange = StatChange(health = 0, intelligence = 5, relationships = 20, wealth = -40, happiness = 25),
                    milestoneTitle = "الحب الأول العفيف"
                ),
                ScenarioChoice(
                    id = "crush_ignore",
                    textAr = "الارتباك الشديد وتجاهل الأمر والتركيز على دراستك فقط",
                    outcomeDescriptionAr = "حافظت على تركيزك الأكاديمي الصارم، رغم تنهيدة خفيفة كلما تذكرت الموقف.",
                    statChange = StatChange(health = 0, intelligence = 15, relationships = -5, wealth = 0, happiness = -5)
                ),
                ScenarioChoice(
                    id = "crush_tell_friends",
                    textAr = "إظهار الرسالة لرفاقك للتباهي وأخذ نصائحهم العشوائية",
                    outcomeDescriptionAr = "انتشر الخبر وصار الجميع يمازحك، وندمت قليلاً على إفشاء سر كان يستحق الخصوصية.",
                    statChange = StatChange(health = 0, intelligence = -10, relationships = 5, wealth = 0, happiness = 5)
                )
            )
        ),
        GameScenario(
            id = "teen_joyride",
            stage = LifeStage.ADOLESCENCE,
            titleAr = "جولة منتصف الليل بسيارة العائلة 🚗",
            storyAr = "كان والداك نائمين في ليلة عطلة نهاية الأسبوع ومفاتيح السيارة على الطاولة، وأصدقاؤك يتحدونك لأخذ جولة استعراضية!",
            emoji = "🚗",
            minAge = 16,
            maxAge = 19,
            choices = listOf(
                ScenarioChoice(
                    id = "joyride_refuse",
                    textAr = "رفض التحدي بحزم وتذكيرهم بالسلامة والقانون",
                    outcomeDescriptionAr = "سخروا منك قليلاً في البداية، لكنهم احترموا رجاحة عقلك في اليوم التالي.",
                    statChange = StatChange(health = 5, intelligence = 15, relationships = -5, wealth = 0, happiness = 5),
                    milestoneTitle = "صوت العقل والمسؤولية"
                ),
                ScenarioChoice(
                    id = "joyride_drive_careful",
                    textAr = "أخذ جولة هادئة جداً حول الحي وإعادة السيارة بدقة",
                    outcomeDescriptionAr = "عشت لحظات إثارة وحرية عالية وأعدت السيارة دون خدش واحد ولا يعلم أحد حتى الآن!",
                    statChange = StatChange(health = 0, intelligence = 5, relationships = 15, wealth = -30, happiness = 20)
                ),
                ScenarioChoice(
                    id = "joyride_reckless",
                    textAr = "الضغط على دواسة السرعة والاستعراض المتهور أمام الرفاق",
                    outcomeDescriptionAr = "صدمت رصيفاً وأتلفت الإطار والصدام! استيقظ الحي بأكمله ودفع والدك فاتورة باهظة مع عقاب تاريخي.",
                    statChange = StatChange(health = -20, intelligence = -15, relationships = -25, wealth = -800, happiness = -25)
                )
            )
        ),
        GameScenario(
            id = "teen_college_choice",
            stage = LifeStage.ADOLESCENCE,
            titleAr = "مفترق الطرق الأكاديمي 🎓",
            storyAr = "حان وقت اختيار تخصصك الجامعي. العائلة تضغط للالتحاق بالطب أو الهندسة، بينما قلبك يميل بشدة نحو البرمجة وريادة الأعمال.",
            emoji = "🎓",
            minAge = 17,
            maxAge = 19,
            choices = listOf(
                ScenarioChoice(
                    id = "college_cs_tech",
                    textAr = "اختيار البرمجة والتكنولوجيا والذكاء الاصطناعي",
                    outcomeDescriptionAr = "دخلت مجال المستقبل! بنيت مشاريع برمجية أولى ونلت منحة تفوق واعدة.",
                    statChange = StatChange(health = 0, intelligence = 25, relationships = 5, wealth = 500, happiness = 20),
                    milestoneTitle = "رائد التكنولوجيا الشاب"
                ),
                ScenarioChoice(
                    id = "college_medicine",
                    textAr = "إرضاء العائلة والتسجيل في كلية الطب والجراحة",
                    outcomeDescriptionAr = "فخر عائلي غير مسبوق واحتفالات صاخبة، مع بداية مشوار دراسي شاق ومسؤوليات جسيمة.",
                    statChange = StatChange(health = -10, intelligence = 25, relationships = 20, wealth = -200, happiness = 5),
                    milestoneTitle = "طبيب المستقبل"
                ),
                ScenarioChoice(
                    id = "college_business",
                    textAr = "دراسة إدارة الأعمال والتجارة الدولية",
                    outcomeDescriptionAr = "تعلمت فن التفاوض والتسويق وتكوين شبكات العلاقات المربحة.",
                    statChange = StatChange(health = 5, intelligence = 15, relationships = 20, wealth = 300, happiness = 15),
                    milestoneTitle = "طموح الأعمال"
                )
            )
        ),

        // ==========================================
        // 👨 الشباب والعمل (20 - 39 سنة)
        // ==========================================
        GameScenario(
            id = "young_job_interview",
            stage = LifeStage.YOUNG_ADULT,
            titleAr = "مقابلة العمل المصيرية 💼",
            storyAr = "جلست أمام لجنة توظيف في شركة رائدة، وبادر المدير بسؤال استفزازي معقد لاختبار مدى ثباتك الانفعالي وسرعة بديهتك.",
            emoji = "💼",
            minAge = 21,
            maxAge = 28,
            choices = listOf(
                ScenarioChoice(
                    id = "job_witty_answer",
                    textAr = "الإجابة بحنكة وثقة مدعمة بالأرقام وحل مبتكر",
                    outcomeDescriptionAr = "انبهر المدير بحضورك الذهني وحصلت على الوظيفة براتب أعلى من المتوقع وسيارة عمل!",
                    statChange = StatChange(health = 0, intelligence = 20, relationships = 15, wealth = 3500, happiness = 25),
                    milestoneTitle = "اقتناص الوظيفة المرموقة"
                ),
                ScenarioChoice(
                    id = "job_modest_honest",
                    textAr = "الاعتراف بعدم معرفة الجواب فوراً مع شرح كيفية البحث عنه",
                    outcomeDescriptionAr = "قدّروا صدقك المهني وتم توظيفك براتب أولي مناسب مع خطة تدريبية.",
                    statChange = StatChange(health = 0, intelligence = 10, relationships = 10, wealth = 1800, happiness = 15)
                ),
                ScenarioChoice(
                    id = "job_arrogant_clash",
                    textAr = "الرد بحدة وإحراج المدير أمام أعضاء اللجنة",
                    outcomeDescriptionAr = "شعرت بنشوة الانتصار للحظات، لكنهم شكروا حضورك وأغلقوا الباب وراءك دون رجعة!",
                    statChange = StatChange(health = 0, intelligence = -5, relationships = -15, wealth = 0, happiness = -10)
                )
            )
        ),
        GameScenario(
            id = "young_marriage_proposal",
            stage = LifeStage.YOUNG_ADULT,
            titleAr = "طلب الزواج وقائمة التكاليف 💍",
            storyAr = "وجدت شريك الحياة المناسب وقررتما الزواج، ولكن متطلبات حفل الزفاف والقاعة الفارهة وشهر العسل تهدد بابتلاع كل مدخراتك!",
            emoji = "💍",
            minAge = 23,
            maxAge = 35,
            choices = listOf(
                ScenarioChoice(
                    id = "marriage_huge_loan",
                    textAr = "أخذ قرض ضخم وإقامة حفل أسطوري لا يُنسى",
                    outcomeDescriptionAr = "كان عرساً حديث المدينة انبهر به الجميع، لكن أقساط القرض المصرفي أثقلت كاهلكما لسنوات.",
                    statChange = StatChange(health = -10, intelligence = -10, relationships = 20, wealth = -15000, happiness = 10),
                    milestoneTitle = "عرس الأحلام والأقساط"
                ),
                ScenarioChoice(
                    id = "marriage_intimate_travel",
                    textAr = "الاتفاق على حفل عائلي بسيط واستثمار الباقي في سفر ممتع",
                    outcomeDescriptionAr = "اتخذتما قراراً حكيماً جمع بين الذكريات الرائعة والحفاظ على الاستقرار المالي.",
                    statChange = StatChange(health = 5, intelligence = 15, relationships = 25, wealth = -3000, happiness = 30),
                    milestoneTitle = "شراكة العمر المباركة"
                ),
                ScenarioChoice(
                    id = "marriage_postpone",
                    textAr = "تأجيل الزواج حتى بناء ثروة أكبر ومحفظة استثمارية",
                    outcomeDescriptionAr = "ركزت على الادخار، لكن التردد تسبب ببعض الفتور العاطفي.",
                    statChange = StatChange(health = 0, intelligence = 10, relationships = -15, wealth = 4000, happiness = -10)
                )
            )
        ),
        GameScenario(
            id = "young_crypto_pitch",
            stage = LifeStage.YOUNG_ADULT,
            titleAr = "إغراء الثراء السريع في الكريبتو 📈",
            storyAr = "اتصل بك صديق العمر متحمساً: 'هناك عملة رقمية جديدة سترتفع ألف ضعف الأسبوع القادم! ضع كل ما تملك وسنشتري جزيرة معاً!'",
            emoji = "🪙",
            minAge = 24,
            maxAge = 36,
            choices = listOf(
                ScenarioChoice(
                    id = "crypto_all_in",
                    textAr = "المخاطرة ووضع نصف مدخراتك في العملة (مقامرة عالية)",
                    outcomeDescriptionAr = "انهارت العملة بعد 48 ساعة وتبين أنها خدعة ترويجية! خسرت مبلغاً مؤلماً وتعلمت درساً قاسياً.",
                    statChange = StatChange(health = -15, intelligence = 5, relationships = -10, wealth = -8000, happiness = -25)
                ),
                ScenarioChoice(
                    id = "crypto_small_risk",
                    textAr = "وضع مبلغ تجريبي رمزي لا يؤثر عليك إطلاقاً",
                    outcomeDescriptionAr = "حققت ربحاً صغيراً لطيفاً وخرجت قبل الانهيار. تسلية آمنة دون أضرار.",
                    statChange = StatChange(health = 0, intelligence = 10, relationships = 5, wealth = 600, happiness = 10)
                ),
                ScenarioChoice(
                    id = "crypto_refuse_lecture",
                    textAr = "رفض العرض وتقديم نصيحة مالية لصديقك بالاستثمار الحقيقي",
                    outcomeDescriptionAr = "شكرك صديقك لاحقاً بعد أن أنقذت جزءاً من أمواله من الضياع، وازدادت ثقته برجاحة عقلك.",
                    statChange = StatChange(health = 5, intelligence = 20, relationships = 15, wealth = 0, happiness = 15),
                    milestoneTitle = "الحكيم المالي"
                )
            )
        ),
        GameScenario(
            id = "young_first_baby",
            stage = LifeStage.YOUNG_ADULT,
            titleAr = "قدوم المولود الأول والليالي البيضاء 👶",
            storyAr = "رزقت بطفلك الأول! امتزجت مشاعر الفرح الغامرة ببكاء لا ينقطع في الثالثة فجراً ورائحة الحفاضات ونقص النوم الحاد.",
            emoji = "👶",
            minAge = 25,
            maxAge = 38,
            choices = listOf(
                ScenarioChoice(
                    id = "baby_share_care",
                    textAr = "تقاسم الاستيقاظ والرعاية مع الشريك بروح الفريق والحب",
                    outcomeDescriptionAr = "عشتما أوقاتاً مجهدة وممتعة عمّقت الرابط الزوجي والأبوي إلى أعلى الدرجات.",
                    statChange = StatChange(health = -10, intelligence = 10, relationships = 30, wealth = -1500, happiness = 25),
                    milestoneTitle = "فرحة الأبوة الأولى"
                ),
                ScenarioChoice(
                    id = "baby_hire_nanny",
                    textAr = "الاستعانة بمساعدة منزلية متخصصة ومربية أطفال",
                    outcomeDescriptionAr = "حافظتما على قسط كافٍ من النوم واستعدتما نشاط العمل مقابل تكلفة شهرية ثابتة.",
                    statChange = StatChange(health = 10, intelligence = 5, relationships = 10, wealth = -3500, happiness = 15)
                ),
                ScenarioChoice(
                    id = "baby_grandparents_help",
                    textAr = "طلب الدعم والمساندة من الأجداد والجدات",
                    outcomeDescriptionAr = "غمر الأجداد الحفيد بالدلال والخبرة العريقة، وأعطوكما فرصة لالتقاط الأنفاس وسط دفء العائلة.",
                    statChange = StatChange(health = 5, intelligence = 5, relationships = 20, wealth = -300, happiness = 20)
                )
            )
        ),
        GameScenario(
            id = "young_startup_vs_stable",
            stage = LifeStage.YOUNG_ADULT,
            titleAr = "الوظيفة الآمنة أم تأسيس شركة ناشئة؟ 🚀",
            storyAr = "عرضت عليك الشركة ترقية دائمة بأمان وظيفي كامل، وفي نفس الوقت تملك فكرة تطبيق ثوري يمكن أن يغير مجالك إذا تفرغت له!",
            emoji = "🚀",
            minAge = 26,
            maxAge = 39,
            choices = listOf(
                ScenarioChoice(
                    id = "startup_found",
                    textAr = "الاستقالة وتأسيس شركتك الخاصة وخوض غمار المغامرة",
                    outcomeDescriptionAr = "سنوات من السهر والعمل الدؤوب، ولكن شركتك نجحت وجذبت أول جولة تمويل استثمارية كبرى!",
                    statChange = StatChange(health = -15, intelligence = 25, relationships = -5, wealth = 18000, happiness = 25),
                    milestoneTitle = "رائد أعمال عصامي"
                ),
                ScenarioChoice(
                    id = "startup_stable_stay",
                    textAr = "البقاء في الوظيفة المستقرة والاستمتاع بعطلات نهاية الأسبوع",
                    outcomeDescriptionAr = "حياة هادئة مستقرة خالية من القلق، مع دخل ثابت وعلاقات متينة مع الأسرة.",
                    statChange = StatChange(health = 15, intelligence = 5, relationships = 15, wealth = 4000, happiness = 15),
                    milestoneTitle = "الاستقرار الوظيفي الرصين"
                ),
                ScenarioChoice(
                    id = "startup_side_hustle",
                    textAr = "إطلاق المشروع كعمل جانبي في المساء مع الاحتفاظ بالوظيفة",
                    outcomeDescriptionAr = "توازن ذكي تطلب جهداً إضافياً، لكنه وفّر لك عائداً ثانياً دون أي مخاطرة برزقك.",
                    statChange = StatChange(health = -5, intelligence = 15, relationships = 5, wealth = 8000, happiness = 15)
                )
            )
        ),

        // ==========================================
        // 🧔 منتصف العمر (40 - 59 سنة)
        // ==========================================
        GameScenario(
            id = "mid_crisis_purchase",
            stage = LifeStage.MIDDLE_AGE,
            titleAr = "أزمة منتصف العمر والقرارات المباغتة 🏍️",
            storyAr = "احتفلت بعيد ميلادك الرابع والأربعين وشعرت فجأة بمرور السنوات بسرعة، ورغبة عارمة في كسر الروتين واستعادة حيوية الشباب!",
            emoji = "🏍️",
            minAge = 42,
            maxAge = 50,
            choices = listOf(
                ScenarioChoice(
                    id = "crisis_sport_bike",
                    textAr = "شراء دراجة نارية رياضية حمراء وسترة جلدية فاخرة",
                    outcomeDescriptionAr = "شعرت بنبض المغامرة في عروقك مجدداً وأصبحت محط تعليقات العائلة المضحكة في كل اجتماع!",
                    statChange = StatChange(health = -5, intelligence = -5, relationships = -5, wealth = -9000, happiness = 25),
                    milestoneTitle = "راكب الدراجات المخضرم"
                ),
                ScenarioChoice(
                    id = "crisis_gym_overhaul",
                    textAr = "الاشتراك في نادٍ رياضي وتغيير نمط الحياة الصحي بالكامل",
                    outcomeDescriptionAr = "استعدت لياقة العشرينات وبنيت جسداً رياضياً رائعاً وحسّنت مؤشراتك الحيوية.",
                    statChange = StatChange(health = 25, intelligence = 10, relationships = 10, wealth = -2000, happiness = 25),
                    milestoneTitle = "الشباب المتجدد"
                ),
                ScenarioChoice(
                    id = "crisis_book_writing",
                    textAr = "البدء في كتابة كتاب يلخص تجاربك وخبراتك الحياتية",
                    outcomeDescriptionAr = "قضيت أمسيات ملهمة في تدوين الذكريات والدروس، وحصلت على تقدير مجتمعي واسع.",
                    statChange = StatChange(health = 5, intelligence = 25, relationships = 10, wealth = 1500, happiness = 20),
                    milestoneTitle = "مؤلف حكيم"
                )
            )
        ),
        GameScenario(
            id = "mid_executive_burnout",
            stage = LifeStage.MIDDLE_AGE,
            titleAr = "كرسي الإدارة العليا والضغط النفسي 🏢",
            storyAr = "عُرض عليك منصب الرئيس التنفيذي مع حزمة أسهم سخية، لكن ذلك يعني العمل 70 ساعة أسبوعياً وسفراً دائماً على حساب عائلتك.",
            emoji = "🏢",
            minAge = 44,
            maxAge = 55,
            choices = listOf(
                ScenarioChoice(
                    id = "ceo_accept_power",
                    textAr = "قبول المنصب واعتلاء قمة الهرم الوظيفي",
                    outcomeDescriptionAr = "حققت ثروة طائلة ونفوذاً مرموقاً، رغم التعب الجسدي والغياب عن المناسبات العائلية.",
                    statChange = StatChange(health = -20, intelligence = 20, relationships = -20, wealth = 35000, happiness = 10),
                    milestoneTitle = "الرئيس التنفيذي البارز"
                ),
                ScenarioChoice(
                    id = "ceo_decline_balance",
                    textAr = "الاعتذار بلباقة وتفضيل التوازن وراحة البال والأسرة",
                    outcomeDescriptionAr = "شكرتك العائلة على هذا الخيار الإنساني النبيل ونعمت بأيام هانئة وصحة ممتازة.",
                    statChange = StatChange(health = 20, intelligence = 5, relationships = 30, wealth = 2000, happiness = 25),
                    milestoneTitle = "حارس السعادة العائلية"
                ),
                ScenarioChoice(
                    id = "ceo_negotiate_terms",
                    textAr = "التفاوض على تفويض الصلاحيات وفريق مساعدين يقلل العبء",
                    outcomeDescriptionAr = "أظهرت حكمة إدارية فذة جمعت بين العائد المادي الممتاز وعدم استنزاف صحتك.",
                    statChange = StatChange(health = 5, intelligence = 20, relationships = 10, wealth = 20000, happiness = 20)
                )
            )
        ),
        GameScenario(
            id = "mid_inheritance_dispute",
            stage = LifeStage.MIDDLE_AGE,
            titleAr = "أزمة الميراث العائلي الشائك 🏡",
            storyAr = "أرض قديمة تركها الأجداد أصبحت داخل النطاق العمراني وارتفعت قيمتها بملايين، وبدأ التوتر والنزاع يدب بين الأقارب على القسمة!",
            emoji = "🏡",
            minAge = 45,
            maxAge = 58,
            choices = listOf(
                ScenarioChoice(
                    id = "inheritance_forgive_peace",
                    textAr = "التنازل عن جزء من نصيبك لإنهاء الخلاف وصلة الرحم",
                    outcomeDescriptionAr = "أنقذت ترابط العائلة بفضل كرمك وتسامحك، وكسبت محبة الجميع وبركة عظيمة في رزقك.",
                    statChange = StatChange(health = 5, intelligence = 15, relationships = 35, wealth = 15000, happiness = 30),
                    milestoneTitle = "مصلح العائلة ورمز التسامح"
                ),
                ScenarioChoice(
                    id = "inheritance_strict_court",
                    textAr = "توكيل محامٍ شرس والمطالبة بكل مليم بالقوة القضائية",
                    outcomeDescriptionAr = "نلت كامل حصتك بالمليم، لكن القلوب انكسرت وتوقفت الزيارات العائلية لسنوات طويلة.",
                    statChange = StatChange(health = -10, intelligence = 10, relationships = -35, wealth = 45000, happiness = -15)
                ),
                ScenarioChoice(
                    id = "inheritance_development_project",
                    textAr = "اقتراح تطوير الأرض وبناء مجمع سكني يملكه الجميع معاً",
                    outcomeDescriptionAr = "حولت الأزمة إلى مشروع تجاري رابح عاد بمداخيل مستمرة لجميع أفراد الأسرة.",
                    statChange = StatChange(health = 5, intelligence = 25, relationships = 20, wealth = 30000, happiness = 25),
                    milestoneTitle = "صانع الشراكات الذكية"
                )
            )
        ),
        GameScenario(
            id = "mid_kids_tuition",
            stage = LifeStage.MIDDLE_AGE,
            titleAr = "دراسة الأبناء في الخارج والتكاليف ✈️",
            storyAr = "حصل ابنك البكر على قبول في جامعة أكسفورد العريقة، والرسوم مع تكاليف المعيشة تستلزم التضحية بجزء كبير من مدخرات تقاعدك.",
            emoji = "🎓",
            minAge = 46,
            maxAge = 58,
            choices = listOf(
                ScenarioChoice(
                    id = "tuition_pay_all",
                    textAr = "دفع كامل التكاليف دون تردد إيماناً بمستقبله المشرق",
                    outcomeDescriptionAr = "تخرج بتفوق وأصبح رمزاً للفخر والامتنان، واعتبر دعمك أعظم استثمار في حياته.",
                    statChange = StatChange(health = 0, intelligence = 15, relationships = 30, wealth = -25000, happiness = 25),
                    milestoneTitle = "السند العظيم للأبناء"
                ),
                ScenarioChoice(
                    id = "tuition_scholarship_work",
                    textAr = "حثّه على التنافس على منحة جزئية والعمل بدوام جزئي",
                    outcomeDescriptionAr = "صقلت شخصيته القيادية واعتمد على نفسه، وتشاركتم النفقات بتوازن ذكي.",
                    statChange = StatChange(health = 5, intelligence = 20, relationships = 15, wealth = -8000, happiness = 20)
                ),
                ScenarioChoice(
                    id = "tuition_local_study",
                    textAr = "إلزامه بالدراسة في الجامعة الوطنية بجوار الأسرة",
                    outcomeDescriptionAr = "وفرت أموال التقاعد وظل بجانبك، وإن حمل في قلبه بعض العتب على الفرصة الضائعة.",
                    statChange = StatChange(health = 0, intelligence = 0, relationships = -15, wealth = 0, happiness = -5)
                )
            )
        ),
        GameScenario(
            id = "mid_medical_warning",
            stage = LifeStage.MIDDLE_AGE,
            titleAr = "جرس الإنذار الصحي المفاجئ 🩺",
            storyAr = "خلال الفحص الدوري الشامل، نظر الطبيب إلى الفحوصات وقال بجدية: 'ضغط الدم والكوليسترول في منطقة الخطر، يجب تغيير عاداتك فوراً!'",
            emoji = "🩺",
            minAge = 48,
            maxAge = 59,
            choices = listOf(
                ScenarioChoice(
                    id = "med_strict_diet",
                    textAr = "الالتزام التام بحمية البحر المتوسط والمشي 10 آلاف خطوة يومياً",
                    outcomeDescriptionAr = "انخفضت المؤشرات لمستوياتها المثالية واستعدت خفتك ونشاطك بشكل أبهر طبيبك!",
                    statChange = StatChange(health = 30, intelligence = 15, relationships = 10, wealth = -500, happiness = 20),
                    milestoneTitle = "الانتصار على جرس الإنذار"
                ),
                ScenarioChoice(
                    id = "med_spa_retreat",
                    textAr = "السفر لمصحة استجمام وعلاج طبيعي في جبال سويسرا",
                    outcomeDescriptionAr = "تجربة استرخاء ملوكية نقّت جسدك وروحك وأعادت إليك صفاء الذهن.",
                    statChange = StatChange(health = 25, intelligence = 10, relationships = 10, wealth = -7000, happiness = 25)
                ),
                ScenarioChoice(
                    id = "med_pills_ignore",
                    textAr = "تناول الحبوب الدوائية فقط والاستمرار في أكل الشواء والمقالي!",
                    outcomeDescriptionAr = "كانت الوجبات شهية، لكن ضغط الدم استمر في التذبذب مسبباً نوبات إرهاق متكررة.",
                    statChange = StatChange(health = -15, intelligence = -5, relationships = 0, wealth = -800, happiness = 5)
                )
            )
        ),

        // ==========================================
        // 👴 الشيخوخة والنهاية (60+ سنة)
        // ==========================================
        GameScenario(
            id = "senior_retirement_party",
            stage = LifeStage.SENIOR,
            titleAr = "حفل التقاعد وبداية الفراغ الذهبي 🏆",
            storyAr = "أقيم لك حفل تكريم مهيب وداعي في مقر عملك، وقُدمت لك ساعة ذهبية تذكارية، وعدت للمنزل لتواجه أول صباح دون منبه!",
            emoji = "🏆",
            minAge = 60,
            maxAge = 68,
            choices = listOf(
                ScenarioChoice(
                    id = "retire_volunteer",
                    textAr = "التطوع في جمعية خيرية ونقل خبرتك للأجيال الشابة",
                    outcomeDescriptionAr = "ملأت أوقاتك بالمعنى والأثر الإيجابي، وأصبحت مرجعاً وقدوة في مجتمعك.",
                    statChange = StatChange(health = 15, intelligence = 15, relationships = 25, wealth = 500, happiness = 30),
                    milestoneTitle = "شيخ الحكمة والمبادرات"
                ),
                ScenarioChoice(
                    id = "retire_coffee_shop",
                    textAr = "افتتاح مقهى ثقافي صغير للقاء أصدقاء العمر يومياً",
                    outcomeDescriptionAr = "تحول مقهاك إلى ملتقى دافئ للضحك والأحاديث والذكريات ولعب الشطرنج.",
                    statChange = StatChange(health = 10, intelligence = 15, relationships = 20, wealth = 4000, happiness = 25),
                    milestoneTitle = "ملتقى الأوفياء"
                ),
                ScenarioChoice(
                    id = "retire_couch_tv",
                    textAr = "الاسترخاء التام على الأريكة ومتابعة كل المسلسلات القديمة",
                    outcomeDescriptionAr = "أيام هادئة بلا أي جهد، وإن بدأت مفاصلك تشكو من قلة الحركة والنشاط.",
                    statChange = StatChange(health = -10, intelligence = -10, relationships = -5, wealth = 0, happiness = 10)
                )
            )
        ),
        GameScenario(
            id = "senior_writing_will",
            stage = LifeStage.SENIOR,
            titleAr = "كتابة الوصية وتوزيع التركة 📜",
            storyAr = "جلست مع كاتب العدل لكتابة وصيتك الأخيرة وتنظيم ما جمعته طوال مسيرتك لتأمين راحة أبنائك وأحفادك من بعدك.",
            emoji = "📜",
            minAge = 65,
            maxAge = 80,
            choices = listOf(
                ScenarioChoice(
                    id = "will_justice_fair",
                    textAr = "توزيع التركة بالعدل التام والشرع مع تخصيص وقف خيري مستدام",
                    outcomeDescriptionAr = "شعر الجميع براحة بال واطمئنان وتقدير لعدلك، ونمت في ليلتك وأنت قرير العين.",
                    statChange = StatChange(health = 5, intelligence = 20, relationships = 30, wealth = 0, happiness = 30),
                    milestoneTitle = "الوصية العادلة"
                ),
                ScenarioChoice(
                    id = "will_charity_majority",
                    textAr = "التبرع بمعظم الثروة لمستشفيات الأطفال والبحث العلمي",
                    outcomeDescriptionAr = "خلّدت اسمك في سجلات الإحسان الإنساني، رغم همس بعض الورثة الطامعين!",
                    statChange = StatChange(health = 5, intelligence = 15, relationships = -10, wealth = -20000, happiness = 35),
                    milestoneTitle = "المحسن الكبير"
                ),
                ScenarioChoice(
                    id = "will_fun_riddle",
                    textAr = "إضافة لغز طريف يشترط على الأبناء الاجتماع أسبوعياً لصرف الريع!",
                    outcomeDescriptionAr = "ضحك الجميع على فكرتك الذكية التي ضمنت عدم تفرق العائلة أبداً.",
                    statChange = StatChange(health = 0, intelligence = 25, relationships = 25, wealth = 0, happiness = 30),
                    milestoneTitle = "حيلة المحبة الأبدية"
                )
            )
        ),
        GameScenario(
            id = "senior_hobby_garden",
            stage = LifeStage.SENIOR,
            titleAr = "بستان الشرفة وعالم طيور الكناري 🪴",
            storyAr = "قررت تخصيص وقتك للعناية بحديقة المنزل وتربية طيور الزينة التي تملأ الصباحات بتغاريدها العذبة.",
            emoji = "🪴",
            minAge = 62,
            maxAge = 85,
            choices = listOf(
                ScenarioChoice(
                    id = "garden_teach_grandkids",
                    textAr = "تعليم الأحفاد أسرار الزراعة وسقاية الشتلات وحب الكائنات",
                    outcomeDescriptionAr = "أحبك الأحفاد بجنون وتعلّقوا بحكاياتك وطيبتك وباتت عطلاتهم لا تحلو إلا عندك.",
                    statChange = StatChange(health = 15, intelligence = 10, relationships = 35, wealth = -300, happiness = 35),
                    milestoneTitle = "الجد الحنون المحبوب"
                ),
                ScenarioChoice(
                    id = "garden_exotic_plants",
                    textAr = "زراعة نباتات نادرة وتوليد سلالات ورد فريدة",
                    outcomeDescriptionAr = "فازت حديقتك بجائزة الحي لأجمل واجهة خضراء وصارت مزاراً للمحبين.",
                    statChange = StatChange(health = 10, intelligence = 20, relationships = 15, wealth = 1000, happiness = 25)
                ),
                ScenarioChoice(
                    id = "garden_parrot",
                    textAr = "شراء ببغاء ذكي ناطق يقلد أصوات أفراد العائلة!",
                    outcomeDescriptionAr = "أشعل الببغاء أجواء المنزل بالضحك المتواصل وهو يفشي تعليقات الضيوف ويزعج الجيران بطرافة!",
                    statChange = StatChange(health = 5, intelligence = 5, relationships = 10, wealth = -500, happiness = 30)
                )
            )
        ),
        GameScenario(
            id = "senior_world_trip",
            stage = LifeStage.SENIOR,
            titleAr = "رحلة العمر وحلم زيارة العالم 🚢",
            storyAr = "لطالما حلمت برحلة بحرية تجوب موانئ البحر المتوسط والشرق الأقصى، والآن تمتلك الوقت والمال لتحقيقها قبل أن يثقلك الكبر.",
            emoji = "🚢",
            minAge = 64,
            maxAge = 82,
            choices = listOf(
                ScenarioChoice(
                    id = "trip_luxury_cruise",
                    textAr = "حجز رحلة كروز بحرية فاخرة برفق شريك الحياة",
                    outcomeDescriptionAr = "عشتما أياماً خيالية من الغروب الساحر على الأمواج وزيارة المدن التاريخية معاً.",
                    statChange = StatChange(health = 10, intelligence = 15, relationships = 30, wealth = -12000, happiness = 35),
                    milestoneTitle = "سندباد الشيخوخة السعيد"
                ),
                ScenarioChoice(
                    id = "trip_family_resort",
                    textAr = "استئجار فيلا ريفية ودعوة جميع الأبناء والأحفاد على حسابك",
                    outcomeDescriptionAr = "اجتمعت الأجيال الثلاثة تحت سقف واحد في أجواء دافئة ستظل محفورة في ذاكرتهم للأبد.",
                    statChange = StatChange(health = 10, intelligence = 5, relationships = 40, wealth = -15000, happiness = 40),
                    milestoneTitle = "لمّة العائلة الكبرى"
                ),
                ScenarioChoice(
                    id = "trip_stay_save",
                    textAr = "تفضيل البقاء في دفء بيتك وادخار المال لعلاج الشيخوخة",
                    outcomeDescriptionAr = "حافظت على أموالك في البنك، وتمنيت لو خطوت خطوة صغيرة نحو المجهول.",
                    statChange = StatChange(health = 0, intelligence = 5, relationships = 0, wealth = 0, happiness = -10)
                )
            )
        ),
        GameScenario(
            id = "senior_old_friend_reconciliation",
            stage = LifeStage.SENIOR,
            titleAr = "لقاء غير متوقع مع صديق الطفولة القديم 🤝",
            storyAr = "أثناء تجولك في السوق القديم، التقت عيناك بصديق طفولتك الذي انقطعت علاقتكما منذ 35 عاماً بسبب خلاف شبابي عابر وتافه!",
            emoji = "🤝",
            minAge = 65,
            maxAge = 90,
            choices = listOf(
                ScenarioChoice(
                    id = "reconcile_hug_tea",
                    textAr = "معانقته بحرارة ودعوته لجلسة شاي واسترجاع ذكريات الصبا",
                    outcomeDescriptionAr = "ذابت كل رواسب السنين في لحظة واحدة، وضحكتما على حماقات الشباب كما لو كنتما في الخامسة عشرة!",
                    statChange = StatChange(health = 15, intelligence = 15, relationships = 35, wealth = -50, happiness = 35),
                    milestoneTitle = "عودة الروح لصداقة العمر"
                ),
                ScenarioChoice(
                    id = "reconcile_cold_nod",
                    textAr = "الاكتفاء بإيماءة رأس باردة والمضي في طريقك",
                    outcomeDescriptionAr = "استمر الكبرياء في فرض شروطه، وشعرت بغصة حنين ضائعة في صدرك.",
                    statChange = StatChange(health = -5, intelligence = -5, relationships = -10, wealth = 0, happiness = -15)
                ),
                ScenarioChoice(
                    id = "reconcile_humorous_joke",
                    textAr = "الاقتراب منه بهدوء وإلقاء نفس النكتة التي تسببت في الخلاف القديم!",
                    outcomeDescriptionAr = "انفجر في الضحك فوراً وقال: 'لم تتغير أبداً يا مجنون!' وتصافحتما بحب كبير.",
                    statChange = StatChange(health = 10, intelligence = 20, relationships = 30, wealth = 0, happiness = 35),
                    milestoneTitle = "ضحكة تذيب ثلاثة عقود"
                )
            )
        )
    )

    // Generator for random humorous annual micro-events
    fun generateAnnualMicroEvent(age: Int, stats: CharacterStats): Pair<String, StatChange> {
        val microEvents = listOf(
            Pair("عثرت على عملة قديمة نادرة في درجك وبيعتها لهاوٍ هاوٍ لمقتنيات التحف! (+500$)", StatChange(wealth = 500, happiness = 10)),
            Pair("أصابك دور إنفلونزا موسمي شتوي وقضيت أسبوعاً كاملاً مع حساء الدجاج والليمون.", StatChange(health = -8, happiness = -5)),
            Pair("شاهدت وثائقياً علمياً مذهلاً عن أسرار الكون زاد من مداركك وتفكيرك الفلسفي.", StatChange(intelligence = 8, happiness = 8)),
            Pair("حصلت على مخالفة مرورية سخيفة بسبب الوقوف المائل أمام مخبز الحي!", StatChange(wealth = -150, happiness = -8)),
            Pair("احتفلت العائلة بعيد ميلادك في مفاجأة مبهجة وسط الهدايا والضحكات الجميلة.", StatChange(relationships = 12, happiness = 15)),
            Pair("قرأت كتاباً قيماً في علم النفس ساعدك على فهم تصرفات الناس من حولك.", StatChange(intelligence = 10, relationships = 5)),
            Pair("انزلقت على صابون الحمام ونجوت بأعجوبة دون كسور ولكن بكدمة مؤلمة!", StatChange(health = -10, happiness = -5)),
            Pair("تطوعت في حملة تشجير الحي والتقيت بأشخاص ملهمين ومحبين للخير.", StatChange(health = 5, relationships = 10, happiness = 12)),
            Pair("ربحت سحباً صغيراً في سوبرماركت محلي وحصلت على قسيمة مشتريات مجانية!", StatChange(wealth = 300, happiness = 10)),
            Pair("أفرطت في شرب القهوة وسهرت 48 ساعة متواصلة وأنت تتخيل مشاريع وهمية!", StatChange(health = -12, intelligence = 5, happiness = -5))
        )
        return microEvents[Random.nextInt(microEvents.size)]
    }

    // Get an event appropriate for current character stage and age
    fun pickScenario(currentAge: Int, playedScenarioIds: Set<String>): GameScenario? {
        val stage = LifeStage.fromAge(currentAge)
        val candidateList = scenarios.filter { scenario ->
            scenario.stage == stage &&
            currentAge >= scenario.minAge &&
            currentAge <= scenario.maxAge &&
            !playedScenarioIds.contains(scenario.id)
        }
        return if (candidateList.isNotEmpty()) {
            candidateList[Random.nextInt(candidateList.size)]
        } else {
            // If already played all matching for this exact age, look for any unplayed in this stage
            val stageCandidates = scenarios.filter { it.stage == stage && !playedScenarioIds.contains(it.id) }
            if (stageCandidates.isNotEmpty()) stageCandidates[Random.nextInt(stageCandidates.size)] else null
        }
    }

    // Determine cause of death when health drops to 0 or extreme age
    fun evaluateDeathCause(age: Int, stats: CharacterStats, isAccident: Boolean = false, fatalReason: String = ""): String {
        if (fatalReason.isNotBlank()) return fatalReason
        if (isAccident) {
            val accidents = listOf(
                "حادث سير غريب بعد محاولة التقاط هاتف سقط تحت مقعد السيارة",
                "سقوط غير متوقع من سلم خشبي أثناء محاولة إنقاذ قطة على الشجرة",
                "صعقة كهربائية أثناء محاولة إصلاح التلفاز بمفك شوكة طعام!"
            )
            return accidents[Random.nextInt(accidents.size)]
        }
        if (stats.health <= 0) {
            val healthFailures = listOf(
                "إجهاد بدني حاد وسكتة قلبية من فرط العمل والإرهاق",
                "مضاعفات صحية ناجمة عن تجاهل الفحوصات الطبية وأوامر الأطباء",
                "نوبة ربو حادة أثناء الركض وراء حافلة عامة في يوم عاصف"
            )
            return healthFailures[Random.nextInt(healthFailures.size)]
        }
        if (age >= 80) {
            val naturalCauses = listOf(
                "وفاة طبيعية هادئة أثناء النوم بسلام بعد حياة مديدة حافلة",
                "توقف نبضات القلب برفق محاطاً بصلوات الأبناء وضحكات الأحفاد",
                "رحيل مهيب لشخصية تركت بصمة لا تُنسى في ذاكرة الأجيال"
            )
            return naturalCauses[Random.nextInt(naturalCauses.size)]
        }
        return "تدهور مفاجئ في المؤشرات الحيوية ونهاية الرحلة الدنيوية"
    }

    // Generate legacy title and karma rating for the memorial screen
    fun generateLegacyTitle(age: Int, stats: CharacterStats, wealth: Long): String {
        return when {
            wealth >= 50000 && stats.intelligence >= 75 -> "الملياردير الفيلسوف والحكيم"
            wealth >= 30000 -> "قطب الأعمال العصامي وبارون المال"
            stats.relationships >= 80 && stats.happiness >= 75 -> "محبوب الملايين ورمز السعادة العائلية"
            stats.intelligence >= 85 -> "العبقري الملهم وصاحب البصيرة"
            stats.health >= 80 && age >= 80 -> "المعمر الرياضي الخالد"
            stats.happiness >= 80 -> "سفير البهجة وروح التفاؤل"
            age >= 90 -> "شيخ المعمرين وحارس الذكريات"
            stats.relationships >= 75 -> "الأب الروحي والعمود الفقري للعائلة"
            stats.wealth < 1000 && stats.happiness >= 70 -> "الزاهد المرح والراضي بنصيبه"
            else -> "المكافح الصبور في دروب الحياة"
        }
    }
}
