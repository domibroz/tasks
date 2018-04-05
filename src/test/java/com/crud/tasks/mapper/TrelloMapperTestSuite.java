package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTestSuite {
    @InjectMocks
    private TrelloMapper trelloMapper;

    @Test
    public void mapToBoardTest() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("1", "ListDto1", false);
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(trelloListDto);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1", "TrelloBoardDto1", trelloListsDto);
        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        trelloBoardsDto.add(trelloBoardDto);

        //When
        String result = trelloMapper.mapToBoard(trelloBoardsDto).get(0).getName();

        //Then
        assertEquals("TrelloBoardDto1", result);
    }

    @Test
    public void mapToBoardDtoTest() {
        //Given
        TrelloList trelloList = new TrelloList("1", "List1", false);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);
        TrelloBoard trelloBoard = new TrelloBoard("1", "TrelloBoard1", trelloLists);
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard);

        //When
        String result = trelloMapper.mapToBoardDto(trelloBoards).get(0).getName();

        //Then
        assertEquals("TrelloBoard1", result);

    }

    @Test
    public void mapToListTest() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("1", "List1", false);
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(trelloListDto);

        //When
        String result = trelloMapper.mapToList(trelloListsDto).get(0).getName();

        //Then
        assertEquals("List1", result);
    }

    @Test
    public void mapToListDtoTest() {
        //Given
        TrelloList trelloList = new TrelloList("1", "List1", false);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);

        //When
        String result = trelloMapper.mapToListDto(trelloLists).get(0).getName();

        //Then
        assertEquals("List1", result);
    }

    @Test
    public void mapToCardDtoTest() {
        //Given
        TrelloCard trelloCard = new TrelloCard("Card1", "Desc1", "up", "1");
        //When
        trelloMapper.mapToCardDto(trelloCard);
        String resultName = trelloMapper.mapToCardDto(trelloCard).getName();
        String resultDesc = trelloMapper.mapToCardDto(trelloCard).getDescription();
        String resultPos = trelloMapper.mapToCardDto(trelloCard).getPos();
        String resultListId = trelloMapper.mapToCardDto(trelloCard).getListId();
        //Then
        assertEquals("Card1", resultName);
        assertEquals("Desc1", resultDesc);
        assertEquals("up", resultPos);
        assertEquals("1", resultListId);
    }

    @Test
    public void mapToCardTest() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("CardDto1", "Desc1", "up", "1");
        //When
        trelloMapper.mapToCard(trelloCardDto);
        String resultName = trelloMapper.mapToCard(trelloCardDto).getName();
        String resultDesc = trelloMapper.mapToCard(trelloCardDto).getDescription();
        String resultPos = trelloMapper.mapToCard(trelloCardDto).getPos();
        String resultListId = trelloMapper.mapToCard(trelloCardDto).getListId();
        //Then
        assertEquals("CardDto1", resultName);
        assertEquals("Desc1", resultDesc);
        assertEquals("up", resultPos);
        assertEquals("1", resultListId);
    }
}
