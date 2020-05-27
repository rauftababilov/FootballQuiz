package com.rauf.footballquiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.rauf.footballquiz.databinding.FragmentQuizBinding

/**
 * A simple [Fragment] subclass.
 */
class QuizFragment : Fragment() {

    private val quizItems: MutableList<QuizItem> = mutableListOf(
        QuizItem("Как комментаторы часто называют игрока, забившего мяч в ворота?",
        listOf("Автор гола", "Инвестор гола", "Дизайнер гола")),
        QuizItem("Во сколько должен закончиться первый тайм футбольного матча, чтобы второй тайм начался в 18:00?",
        listOf("В 17:45", "В 17:40", "В 17:30")),
        QuizItem("Что получит футболист, затеявший драку на футбольном поле?",
        listOf("Красную карточку", "Желтую карточку", "Приз от Федерации бокса")),
        QuizItem("Как называют игрока, ведущего мяч?",
        listOf("Дриблер", "Драйвер", "Дилер")),
        QuizItem("Как называется обувь футболиста?",
        listOf("Бутсы", "Чешки", "Шиповки")),
        QuizItem("Какой способ удара по мячу есть в футболе?",
        listOf("Щёчка", "Носик", "Ротик")),
        QuizItem("Как в футболе называют иностранного игрока клубной команды?",
        listOf("Легионер", "Ополченец", "Партизан")),
        QuizItem(" Какой из этих сборных команд по футболу не существует?",
        listOf("Великобритании", "Албании", "Сан-Марино")),
        QuizItem(" Какое игровое амплуа было у футболиста Диего Марадоны?",
        listOf("Нападающий", "Вратарь", "Защитник")),
        QuizItem("Как по-другому называется боковая линия футбольного поля?",
        listOf("Бровка", "Волосок", "Рёбрышко")),
        QuizItem("Как называется мяч, забитый футболистом в ворота своей команды?",
        listOf("Автогол", "Мотогол", "Аэрогол")),
        QuizItem(" Какого игрока нет в футбольной команде?",
        listOf("Полунападающий", "Полузащитник", "Защитник")),
        QuizItem("Что назначает судья проштрафившейся команде футболистов?",
        listOf("Штрафной удар", "Штрафное очко", "Штрафные санкции")),
        QuizItem("Что учитывается, когда футболиста называют лучшим бомбардиром?",
        listOf("Забитые голы", "Жёлтые карточки", "Цвет формы")),
        QuizItem("Как первоначально назывался Кубок УЕФА?",
        listOf("«Кубок ярмарок»", "«Фужер базаров»", "«Бокал рынков»")),
        QuizItem(" Как называется известный футбольный приз?",
        listOf("«Золотая бутса»", "«Серебряная подкова»", "«Хрустальная туфелька»")),
        QuizItem("Какой приз ежегодно вручается лучшему бомбардиру европейских национальных чемпионатов по футболу?",
        listOf("«Золотая бутса»", "«Золотой мяч»", "«Золотые гетры»")),
        QuizItem(" Как в футболе называется глухая защита?",
        listOf("Бетон", "Гранит", "Кирпич")),
        QuizItem("Как футбольный комментатор обычной говорит о команде, на стадионе которой проходит матч?",
        listOf("Хозяева поля", "Владельцы ворот", "Властелины мяча")),
        QuizItem("Как в футболе называют ведение мяча игроком?",
        listOf("Дриблинг", "Тюнинг", "Шейпинг")),
        QuizItem("Кто в 1958 году стал самым юным чемпионом мира и позднее получил титул «король футбола»?",
        listOf("Пеле", "Марадона", "Гарринча")),
        QuizItem("Родина самой популярной в мире игры?",
        listOf("Англия", "США", "Бразилия")),
        QuizItem("Сколько минут длится футбольный матч?",
        listOf("90", "80", "120")),
        QuizItem("За сборную какой страны играл футболист Марадона?",
        listOf("Аргентина", "Италия", "Португалия")),
        QuizItem("В каком году и где прошел первый в истории Кубок мира ФИФА?",
        listOf("в 1930 году в Уругвае", "в 1936 в Англии", "в 1950 в Бразилии")),
        QuizItem("Сколько игроков у каждой команды на поле, когда начинается футбольный матч?",
        listOf("11", "8", "12")),
        QuizItem("За исключением вратаря, какая часть тела у игроков не может касаться мяча?",
        listOf("Рука", "Голова", "Спина"))
    )

    lateinit var currentQuizItem: QuizItem
    lateinit var answers:  MutableList<String>
    private var quizItemIndex = 0
    private val numberOfQuestions = 5

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentQuizBinding>(
            inflater, R.layout.fragment_quiz, container, false
        )

        getRandomQuizItem()

        binding.quizFragment = this

        binding.passButton.setOnClickListener {view: View ->

            val selectedCheckBoxId = binding.quizRadioGroup.checkedRadioButtonId

            if (selectedCheckBoxId != -1) {

                var answerIndex = 0
                when (selectedCheckBoxId) {

                    R.id.firstRadioButton -> answerIndex = 0
                    R.id.secondRadioButton -> answerIndex = 1
                    R.id.thirdRadioButton -> answerIndex = 2
                }

                if (answers[answerIndex] == currentQuizItem.answerList[0]) {

                    quizItemIndex++
                    if (quizItemIndex < numberOfQuestions) {

                        setQuizItem()
                        binding.invalidateAll()

                    }

                } else {

                    //Go to goalFragment
                    view.findNavController().navigate(
                        R.id.action_quizFragment_to_goalFragment
                    )
                }

            } else {

                //Go to missFragment
                view.findNavController().navigate(
                    R.id.action_quizFragment_to_missFragment
                )
            }
        }

        return binding.root
    }

    private fun getRandomQuizItem() {
        quizItems.shuffle()
        quizItemIndex = 0
        setQuizItem()

    }

    private fun setQuizItem() {

        currentQuizItem = quizItems[quizItemIndex]
        answers = currentQuizItem.answerList.toMutableList()
        answers.shuffle()
    }

}
