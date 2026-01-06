import {useState} from 'react';
import {Button, Card, Modal} from 'react-bootstrap'


function Map() {
    const [maps, setMaps] = useState([])
    const [countId, setCountId] = useState(1)
    const [modalShow, setModalShow] = useState(false);


    function newMap() {
        let newList = []
        let newMap = {
            id: countId, nomeMapa: 'Mapa 1'
        }
        newList = [...maps, newMap];
        setMaps(newList);
        setCountId(countId + 1)

    }

    function excludeMap(removePorId) {
        let novaList = maps.filter(mapa => mapa.id !== removePorId)
        setMaps(novaList)

    }

    function excludeAll() {
        let listaSemNada = [];
        setMaps(listaSemNada)
    }

    return (<>
        <Modal
            show={modalShow}
            onHide={() => setModalShow(false)}
            size="lg"
            aria-labelledby="contained-modal-title-vcenter"
            centered
        >

            <Modal.Body>
                <h4>Tem certeza que quer excluir todos os mapas?</h4>
            </Modal.Body>
            <Modal.Footer>
                <Button onClick={() => {
                    excludeAll();
                    setModalShow(false);
                }}>Sim</Button>
                <Button onClick={() => setModalShow(false)}>Não</Button>
            </Modal.Footer>
        </Modal>
        <Button onClick={newMap} className='botaoCria'>+ Criar novo mapa</Button>
        <Card> {maps.map(mapa => (<Card key={mapa.id}>
            <ul>
                <li>{mapa.nomeMapa}< /li>
                <Button onClick={() => excludeMap(mapa.id)}>Excluir</Button>
            </ul>
        </Card>))}
            <Button variant="primary" onClick={() =>
                setModalShow(true)}>Excluir tudo</Button></Card>

    </>)
}

export default Map